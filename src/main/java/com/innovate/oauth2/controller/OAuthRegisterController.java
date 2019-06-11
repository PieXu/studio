package com.innovate.oauth2.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.oauth2.model.OAuthClient;
import com.innovate.oauth2.service.IOAuthRegisterService;
import com.innovate.oauth2.util.Constants;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.dic.service.impl.DicFactory;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;
import com.innovate.util.LoggerUtils;
import com.innovate.util.MD5Utils;
import com.innovate.util.ResultObject;

/**
 * 授权控制器AuthorizeController
 * @author IvanHsu
 * @2018年8月10日 上午11:00:44
 */
@Controller
public class OAuthRegisterController extends BaseController {
	
	@Autowired  
    private IOAuthRegisterService authRegService;  
	//数据字典
	private DicUtil dicUtil = DicFactory.getDicUtil();
	
	/**      
	 * 客户端注册的列表
	 * @param request
	 * @param model
	 * @param client
	 * @param pageNum
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="oauth2/regList")
	public String registerList(HttpServletRequest request,Model model,OAuthClient client,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<OAuthClient> page = authRegService.listRegisterList(client);
		model.addAttribute("page",page);
		//页面需要的数据字典
		model.addAttribute("dicList", dicUtil.getDicList(Constants.GRANT_TYPE_DIC));
		model.addAttribute("client", client);
		return "oauth/oauthRegList";
	} 
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"oauth2/addOAuthReg","oauth2/editOAuthReg"})
	public String modifyOAuthReg(HttpServletRequest request,Model model,String id)
	{
		OAuthClient client = new OAuthClient();
		if(!StringUtils.isEmpty(id)){
			client =  authRegService.getById(id);
		}
		model.addAttribute("client", client);
		//页面需要的数据字典
		model.addAttribute("dicList", dicUtil.getDicList(Constants.GRANT_TYPE_DIC));
		
		return "oauth/nsm/oauthEdit";
	}
	
	/**
	 * 保存
	 * @param request
	 * @param model
	 * @param breaing
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="oauth2/saveOAuthReg")
	public ResultObject saveOAuthReg(HttpServletRequest request,Model model,OAuthClient client)
	{
		ResultObject result = new ResultObject();
		String id = client.getId();
		try{
			if(!StringUtils.isEmpty(id)){
				authRegService.updateOAuthClient(client);
				result.setMessage("操作更新成功");
			}else{
				//设置主键，非自增长
				client.setId(IdUtil.getUUID());
				//客户端秘钥
				String clientSecret = MD5Utils.encoder(client.getClientId()+new Date().getTime());
				client.setClientSecret(clientSecret);
				//token时长设置
				client.setAccessTokenValidity(Constants.ACCESS_TOKEN_SECONDS);
				client.setRefreshTokenValidity(Constants.REFRESH_TOKEN_SECONDS);
				
				authRegService.saveOAuthClient(client);
				result.setMessage("操作添加成功");
			}
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		}catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(this.getClass(),"产品更新保存异常："+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除对象
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="oauth2/deleteOAuth")
	public ResultObject deleteOAuth(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				authRegService.deleteById(id);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("删除操作成功!");
			}catch (Exception e) {
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("删除操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
	
//	@RequestMapping("/authorize")
//	public Object authorize(Model model, HttpServletRequest request)
//	{
//		try {
//			// 构建OAuth 授权请求
//			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
//			// 检查传入的客户端id是否正确
//			if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
//				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
//						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
//						.setErrorDescription(Constants.INVALID_CLIENT_DESCRIPTION).buildJSONMessage();
//				return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
//			}
//
//			Subject subject = SecurityUtils.getSubject();
//			// 如果用户没有登录，跳转到登陆页面
//			if (!subject.isAuthenticated()) {
//				if (!login(subject, request)) {// 登录失败时跳转到登陆页面
//					model.addAttribute("client", clientService.findByClientId(oauthRequest.getClientId()));
//					return "oauth2login";
//				}
//			}
//
//			String username = (String) subject.getPrincipal();
//			// 生成授权码
//			String authorizationCode = null;
//			// responseType目前仅支持CODE，另外还有TOKEN
//			String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
//			if (responseType.equals(ResponseType.CODE.toString())) {
//				OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
//				authorizationCode = oauthIssuerImpl.authorizationCode();
//				oAuthService.addAuthCode(authorizationCode, username);
//			}
//			// 进行OAuth响应构建
//			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,
//					HttpServletResponse.SC_FOUND);
//			// 设置授权码
//			builder.setCode(authorizationCode);
//			// 得到到客户端重定向地址
//			String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
//
//			// 构建响应
//			final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
//			// 根据OAuthResponse返回ResponseEntity响应
//			HttpHeaders headers = new HttpHeaders();
//			headers.setLocation(new URI(response.getLocationUri()));
//			return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
//		} catch (OAuthProblemException e) {
//			// 出错处理
//			String redirectUri = e.getRedirectUri();
//			if (OAuthUtils.isEmpty(redirectUri)) {
//				// 告诉客户端没有传入redirectUri直接报错
//				return new ResponseEntity("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
//			}
//			// 返回错误消息（如?error=）
//			final OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(e)
//					.location(redirectUri).buildQueryMessage();
//			HttpHeaders headers = new HttpHeaders();
//			headers.setLocation(new URI(response.getLocationUri()));
//			return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
//		}
//	}

}
