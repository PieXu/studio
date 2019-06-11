package com.innovate.oauth2.util;

/**
 * 认证授权相关的常量设置类
 * 
 * @author IvanHsu
 * @2018年8月21日 上午11:11:23
 */
public class Constants {

	/**
	 * TOKEN 时长
	 */
	public static final String ACCESS_TOKEN_SECONDS = "7200";

	/**
	 * 刷新token时长
	 */
	public static final String REFRESH_TOKEN_SECONDS = "72000";

	/**
	 * 授权类型
	 */
	public static final String GRANT_TYPE_DIC = "OAUTH_GRANT_TYPE";

	/**
	 * 认证失败的跳转链接
	 */
	public static final String OAUTH_AUTHORIZED_FAILED_URL = "oauth/nsm/oauth2_authorize_fail";

	/**
	 * 认证失败的消息key
	 */
	public static final String OAUTH_AUTHORIZE_FAILED_KEY = "OAuth2FailedMessage";
	
	/**
	 * 认证失败的消息key
	 */
	public static final String OAUTH_AUTHORIZE_FAILED_CODE = "OAuth2FailedCode";
	
	
	public static final String BASE_DOMAIN_URL = "http://localhost:9000/studio";
	
	/**
	 * 验证code的回调函数URL
	 */
	public static final String  OAUTH_REDIRECT_CODE_CALLBACK_URL = BASE_DOMAIN_URL + "/oauth2_server/code_callback.do";
	
	/**
	 *code的回调函数URL
	 */
	public static final String  OAUTH_REDIRECT_AUTH_CODE_URL = BASE_DOMAIN_URL + "/oauth2/authorize_code.do";

	/**
	 * token的回调函数URL
	 */
	public static final String  OAUTH_REDIRECT_ACCESS_TOKEN_URL = BASE_DOMAIN_URL + "/oauth2/access_token.do";
	
	/**
	 * 获取资源URL
	 */
	public static final String  OAUTH_REDIRECT_AUTH_USER_URL = BASE_DOMAIN_URL + "/oauth2/authUser.do";
	
	/**
	 * 获取资源URL
	 */
	public static final String  OAUTH_REDIRECT_ACCESS_AUTH_URL = BASE_DOMAIN_URL + "/oauth2_server/access_auth.do";
	
	

	/**
	 * 授权失败认证码
	 */
	public static final String AUTH_INVILID_CLIENT = "1001";
	

}
