package com.innovate.oauth2.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.innovate.oauth2.service.IOAuthService;
import com.innovate.oauth2.util.Constants;
import com.innovate.util.HttpClientUtils;
import com.innovate.util.LoggerUtils;

/**
 * 登录认证服务
 * @author IvanHsu
 * @2018年8月23日 下午2:49:07
 */
@Controller
@RequestMapping("oauth2_server")
public class OAuthServerController {

	@Autowired
	private IOAuthService oAuthService; 
	
	/**
	 * 跳转授权登录页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws OAuthProblemException
	 */
	@RequestMapping("/show")
	public String showLoginPage(HttpServletRequest request, HttpServletResponse response, Model model)
			throws OAuthProblemException {
		
		String clientId = request.getParameter(OAuth.OAUTH_CLIENT_ID);
		String responseType = request.getParameter(OAuth.OAUTH_RESPONSE_TYPE);
		// 设置授权的类型参数
		if(ResponseType.CODE.toString().equalsIgnoreCase(responseType)){
			model.addAttribute(OAuth.OAUTH_GRANT_TYPE, GrantType.AUTHORIZATION_CODE);
		}
		if(StringUtils.isNotBlank(clientId)){
			com.innovate.oauth2.model.OAuthClient authClient = oAuthService.getOAuthClientByClientId(clientId);
			if (null == authClient) {
				model.addAttribute(Constants.OAUTH_AUTHORIZE_FAILED_KEY, "无效的客户端Id");
				model.addAttribute(Constants.OAUTH_AUTHORIZE_FAILED_CODE, Constants.AUTH_INVILID_CLIENT);
				return Constants.OAUTH_AUTHORIZED_FAILED_URL;
			}
			model.addAttribute("clientName", authClient.getClientName());
		}
		
		//存在客户端的时候反馈客户端信息，并返回参数保存
		model.addAttribute(OAuth.OAUTH_CLIENT_ID, clientId);
		model.addAttribute(OAuth.OAUTH_REDIRECT_URI, request.getParameter(OAuth.OAUTH_REDIRECT_URI));
		return "oauth/nsm/oauth2_login";
	}
	
	
	/**
	 * 客户端访问的请求
	 * 提交申请认证，业务逻辑完全放在服务端来处理， 只保留最后的传递参数，用于授权信息的查询
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 * @throws OAuthProblemException
	 */
	@RequestMapping("/authorize")
	public String requestServerFirst(HttpServletRequest request, HttpServletResponse response, Model model)
			throws OAuthProblemException {
		/*
		 * 1、获取对应设置的参数
		 */
		String clientId = request.getParameter(OAuth.OAUTH_CLIENT_ID);
		String grantType = request.getParameter(OAuth.OAUTH_GRANT_TYPE);
		String redirectUri = request.getParameter(OAuth.OAUTH_REDIRECT_URI);
		if(GrantType.AUTHORIZATION_CODE.toString().equalsIgnoreCase(grantType)){// 授权类型为认证码的时候处理
			String requestUrl = null;
			try {
				// 构建oauthd的请求。设置请求服务地址（accessTokenUrl）、clientId、response_type、redirectUrl
				OAuthClientRequest accessTokenRequest = OAuthClientRequest
						.authorizationLocation(Constants.OAUTH_REDIRECT_AUTH_CODE_URL)
						.setResponseType(ResponseType.CODE.toString())
						.setClientId(clientId)
						.setRedirectURI(redirectUri)
						.setParameter(OAuth.OAUTH_USERNAME, request.getParameter(OAuth.OAUTH_USERNAME))
						.setParameter(OAuth.OAUTH_PASSWORD, request.getParameter(OAuth.OAUTH_PASSWORD))
						.buildQueryMessage();
				requestUrl = accessTokenRequest.getLocationUri();
			} catch (OAuthSystemException e) {
				LoggerUtils.error(getClass(), e.getMessage());
			}
			return "redirect:" + requestUrl;
		} 
		model.addAttribute(Constants.OAUTH_AUTHORIZE_FAILED_KEY, "不支持当前的认证方式");
		return Constants.OAUTH_AUTHORIZED_FAILED_URL;
	}

	/**
	 * code获取之后的处理函数
	 * @param request
	 * @return
	 * @throws OAuthProblemException
	 */
	@RequestMapping("/code_callback")
	public Object toLogin(HttpServletRequest request,Model model) throws OAuthProblemException 
	{
		/*
		 * 
		 */
		String authCode = request.getParameter(OAuth.OAUTH_CODE);
		if (StringUtils.isNotBlank(authCode)) {
			// 使用授权码去服务端获取令牌
			if (oAuthService.checkAuthCode(authCode)) {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put(OAuth.OAUTH_STATE, request.getParameter(OAuth.OAUTH_STATE));	
				paramMap.put(OAuth.OAUTH_SCOPE, request.getParameter(OAuth.OAUTH_SCOPE));
				paramMap.put(OAuth.OAUTH_GRANT_TYPE, String.valueOf(GrantType.AUTHORIZATION_CODE));
				paramMap.put(OAuth.OAUTH_CLIENT_ID, request.getParameter(OAuth.OAUTH_CLIENT_ID));
				paramMap.put(OAuth.OAUTH_CODE, authCode);
				paramMap.put(OAuth.OAUTH_CLIENT_SECRET, request.getParameter(OAuth.OAUTH_CLIENT_SECRET));
				paramMap.put(OAuth.OAUTH_REDIRECT_URI, request.getParameter(OAuth.OAUTH_REDIRECT_URI));
				paramMap.put(OAuth.OAUTH_USERNAME, request.getParameter(OAuth.OAUTH_USERNAME));
				paramMap.put(OAuth.OAUTH_PASSWORD, request.getParameter(OAuth.OAUTH_PASSWORD));
				String accessToken = HttpClientUtils.doPost(Constants.OAUTH_REDIRECT_ACCESS_TOKEN_URL, paramMap);
				model.addAttribute(OAuth.OAUTH_ACCESS_TOKEN, accessToken);
				model.addAttribute(OAuth.OAUTH_REDIRECT_URI, request.getParameter(OAuth.OAUTH_REDIRECT_URI));
				return "redirect:" + Constants.OAUTH_REDIRECT_ACCESS_AUTH_URL;
			}
		}
		return null;

	}

	/**
	 * 获取授权信息
	 * @param request
	 * @param model
	 * @return
	 * @throws OAuthSystemException 
	 * @throws OAuthProblemException 
	 */
	@RequestMapping("/access_auth")
	public ModelAndView accessAuth(HttpServletRequest request,Model model) throws OAuthSystemException, OAuthProblemException 
	{
		String redirectUri = request.getParameter(OAuth.OAUTH_REDIRECT_URI);
		String accessToken = request.getParameter(OAuth.OAUTH_ACCESS_TOKEN);
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(Constants.OAUTH_REDIRECT_AUTH_USER_URL)
				.setAccessToken(accessToken)
				.buildQueryMessage();
		OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.POST,
				OAuthResourceResponse.class);
		String resp = resourceResponse.getBody();
		Object o = JSONObject.parseObject(resp);
		model.addAttribute("userinfo", o.toString());
		model.addAttribute("str", o); 
		model.addAttribute("str1", resp);
		//如果存在调用的url则回调，如果不存在则回调默认的认证成功提示页面
		if(StringUtils.isNotBlank(redirectUri)){
			return new ModelAndView("redirect:" + redirectUri).addObject("userinfo",resp);
		}
		return null;
	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request,Model model) throws OAuthSystemException, OAuthProblemException 
	{
		String  userinfo = request.getParameter("userinfo");
		Object str = request.getParameter("str");
		Object str1 = request.getParameter("str1");
		model.addAttribute("userinfo",new String(userinfo));
		return "oauth/test";
	}
}
