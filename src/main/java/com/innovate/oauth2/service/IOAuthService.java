package com.innovate.oauth2.service;

import com.innovate.oauth2.model.OAuthClient;

/**
 * 认证授权service
 * @author IvanHsu
 * @2018年8月10日 上午10:56:17
 */
public interface IOAuthService {

	/**
	 * 添加 auth code
	 * @param authCode
	 * @param username
	 */
	public void addAuthCode(String authCode, String username); 

	/**
	 * 添加  accesstoken
	 * @param access Token
	 * @param username
	 */
	public void addAccessToken(String accessToken, String username);  

	/**
	 * 验证auth code是否有效
	 * @param authCode
	 * @return
	 */
	boolean checkAuthCode(String authCode);  

	/**
	 *  验证access token是否有效
	 * @param accessToken
	 * @return
	 */
	boolean checkAccessToken(String accessToken); 

	/**
	 *  根据auth code获取用户名
	 * @param authCode
	 * @return
	 */
	public String getUsernameByAuthCode(String authCode);

	/**
	 * 根据access token获取用户名
	 * @param accessToken
	 * @return
	 */
	public String getUsernameByAccessToken(String accessToken);

	/**
	 * 检查客户端安全KEY是否存在
	 * @param clientSecret
	 * @return
	 */
	public boolean checkClientSecret(String clientSecret);

	/**
	 * 
	 * @param clientId
	 * @return
	 */
	public OAuthClient getOAuthClientByClientId(String clientId);

}
