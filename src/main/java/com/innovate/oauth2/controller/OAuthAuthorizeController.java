package com.innovate.oauth2.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.innovate.basic.base.BaseController;
import com.innovate.oauth2.model.OAuthClient;
import com.innovate.oauth2.service.IOAuthService;
import com.innovate.oauth2.util.Constants;
import com.innovate.user.user.model.User;
import com.innovate.user.user.service.IUserService;
import com.innovate.util.LoggerUtils;

/**
 * 认证服务 提供
 * 
 * @author IvanHsu
 * @2018年8月21日 下午3:04:04
 */
@Controller
@RequestMapping("oauth2")
public class OAuthAuthorizeController extends BaseController {

	@Autowired
	private IOAuthService oAuthService;
	@Autowired
	private IUserService userServer;

	/**
	 * 授权码获取
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("authorize_code")
	public String getAuthorizeCode(Model model, HttpServletRequest request) throws Exception {
		OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
		String clientId = oauthRequest.getClientId();
		String userName = oauthRequest.getParam(OAuth.OAUTH_USERNAME);
		String userPassword = oauthRequest.getParam(OAuth.OAUTH_PASSWORD);
		if (StringUtils.isNotBlank(clientId)) {
			/**
			 * 1、 检查clientID是否存在
			 */
			OAuthClient authClient = oAuthService.getOAuthClientByClientId(clientId);
			if (null == authClient) {
				model.addAttribute(Constants.OAUTH_AUTHORIZE_FAILED_KEY, "无效的客户端Id");
				model.addAttribute(Constants.OAUTH_AUTHORIZE_FAILED_CODE, Constants.AUTH_INVILID_CLIENT);
				
				return Constants.OAUTH_AUTHORIZED_FAILED_URL;
			}
			/**
			 * 2、存在应用Client, 生成授权码
			 */
			String username = authClient.getClientName();
			String authCode = null;
			String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
			/**
			 * 3、 ResponseType仅支持CODE和TOKEN
			 */
			if (responseType.equals(ResponseType.CODE.toString())) {
				OAuthIssuerImpl oAuthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
				authCode = oAuthIssuerImpl.authorizationCode();
				oAuthService.addAuthCode(authCode, username);// 将code存放缓存
				
				/*
				 * 1、 进行OAuth响应构建
				 */
				OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,
						HttpServletResponse.SC_FOUND);
				/*
				 * 2、 设置授权码
				 */
				builder.setCode(authCode);
				/*
				 * 3、得到到客户端重定向地址， 构建响应,并返回
				 */
				OAuthResponse response = builder.location(Constants.OAUTH_REDIRECT_CODE_CALLBACK_URL).buildQueryMessage();
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(new URI(response.getLocationUri()));
				model.addAttribute(OAuth.OAUTH_CLIENT_ID, clientId);
				model.addAttribute(OAuth.OAUTH_CLIENT_SECRET, authClient.getClientSecret());
				model.addAttribute(OAuth.OAUTH_CODE, authCode);
				model.addAttribute(OAuth.OAUTH_USERNAME, userName);
				model.addAttribute(OAuth.OAUTH_PASSWORD, userPassword);
				model.addAttribute(OAuth.OAUTH_REDIRECT_URI, oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI));//客户端需要回调的地址
				return "redirect:" + Constants.OAUTH_REDIRECT_CODE_CALLBACK_URL;
			}else{ // token 和 其他类型 先做不支持处理
				model.addAttribute(Constants.OAUTH_AUTHORIZE_FAILED_KEY, "服务不支持请求的类型");
				return Constants.OAUTH_AUTHORIZED_FAILED_URL;
			}
		} else {
			model.addAttribute(Constants.OAUTH_AUTHORIZE_FAILED_KEY, "应用参数ID不能为空，请确认");
			return Constants.OAUTH_AUTHORIZED_FAILED_URL;
		}
	}

	/**
	 * 获取客户端的code码，向客户端返回access token
	 * 
	 * @param request
	 * @return
	 * @throws OAuthProblemException
	 * @throws OAuthSystemException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "access_token")
	public HttpEntity getAccessToken(Model model,HttpServletRequest request) throws OAuthSystemException, OAuthProblemException 
	{
		OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);
		/**
		 * 1、 检查clientID是否存在
		 */
		String clientId = tokenRequest.getClientId();
		OAuthClient authClient = oAuthService.getOAuthClientByClientId(clientId);
		if (null == authClient) {
			OAuthResponse oAuthResponse = OAuthASResponse
					.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
					.setError(OAuthError.TokenResponse.INVALID_CLIENT)
					.setErrorDescription("无效的客户端Id")
					.buildJSONMessage();
			return new ResponseEntity(oAuthResponse.getBody(), HttpStatus.valueOf(oAuthResponse.getResponseStatus()));
		}
		
		/**
		 * 2、检查客户端安全KEY是否正确
		 */
		String clientSecret = tokenRequest.getClientSecret();
		if(!oAuthService.checkClientSecret(clientSecret)){
			OAuthResponse response = OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						.setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
						.setErrorDescription("客户端安全KEY认证失败，检查参数设置！")
						.buildJSONMessage();
			return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
		}
		
		/**
		 * 3、验证类型，有AUTHORIZATION_CODE/PASSWORD/REFRESH_TOKEN/CLIENT_CREDENTIALS
		 */
		String authCode = tokenRequest.getParam(OAuth.OAUTH_CODE);
		if(tokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())){
			if(!oAuthService.checkAuthCode(authCode)){
				OAuthResponse response = OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_GRANT)
		                .setErrorDescription("错误的授权码")  
		                .buildJSONMessage();
				return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
			}
		}
		
		/**
		 * 4、生成访问令牌
		 */
		OAuthIssuerImpl authIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		String accessToken = authIssuerImpl.accessToken();
		String refreshToken = authIssuerImpl.refreshToken();
		LoggerUtils.debug(getClass(), "服务端生成的AccessToken：" + accessToken);
		String username = tokenRequest.getUsername();
		String password = tokenRequest.getPassword();
		JSONObject userJson = new JSONObject();
		userJson.put("username", username);
		userJson.put("password", password);
		oAuthService.addAccessToken(accessToken, userJson.toJSONString());
		/**
		 * 5、 根据OAuthResponse生成ResponseEntity，生成OAuth响应
		 */
		OAuthResponse response = OAuthASResponse
				.tokenResponse(HttpServletResponse.SC_OK)
				.setAccessToken(accessToken)
				.setRefreshToken(refreshToken)
				.setExpiresIn(Constants.ACCESS_TOKEN_SECONDS)
				.buildJSONMessage();
		return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
	}

	/**
	 * 获取授权的资源信息 ，目前只返回 登录的用户的信息
	 * @param request
	 * @return
	 * @throws OAuthSystemException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value="/authUser")
	public HttpEntity getAuthrizedRes(HttpServletRequest request) throws OAuthSystemException 
	{
		try {
			OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
			/**
			 * 1、 获取Access Token，并验证
			 */
			String accessToken = oauthRequest.getAccessToken();
			LoggerUtils.debug(getClass(), request.getRemoteAddr() + " 传递的token信息：" + accessToken);
			/*
			 * 如果不存在/过期了，返回未验证错误，需重新验证
			 */
			
	        String accessTokenKey = "";
	        if(StringUtils.isNotBlank(accessToken)) {
	        	JSONObject json = JSONObject.parseObject(accessToken);
	        	if(json.containsKey(OAuth.OAUTH_ACCESS_TOKEN)){
	        		accessTokenKey = json.getString(OAuth.OAUTH_ACCESS_TOKEN);
	        	}
	        }
			if(!oAuthService.checkAccessToken(accessTokenKey)){
				OAuthResponse oauthResponse = OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						.setRealm("Apache Oltu").setError(OAuthError.ResourceResponse.INVALID_TOKEN)
						.buildHeaderMessage();

				HttpHeaders headers = new HttpHeaders();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE,oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
			}
			/**
			 *  返回用户名
			 */
			String userJson = oAuthService.getUsernameByAccessToken(accessTokenKey);
			JSONObject jsonUser = JSONObject.parseObject(userJson);
			User user = userServer.queryUserByLoginName(jsonUser.getString("username"));
			JSONObject json = new JSONObject();
			json.put("loginname", user.getLoginName());
			json.put("name", user.getUserName());
			json.put("age", user.getAge());
			return new ResponseEntity(json, HttpStatus.OK);

		} catch (OAuthProblemException e) {
			LoggerUtils.error(this.getClass(), e.getMessage());
			/**
			 * 3、检查是否设置了错误码
			 */
			String errorCode = e.getError();
			HttpHeaders headers = new HttpHeaders();
			if (OAuthUtils.isEmpty(errorCode)) {
				OAuthResponse oauthResponse = 
						OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED).buildHeaderMessage();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE,
						oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
			}else{
				OAuthResponse oauthResponse = OAuthRSResponse
						.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						.setError(e.getError())
						.setErrorDescription(e.getDescription())
						.setErrorUri(e.getUri())
						.buildHeaderMessage();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}
	}

}
