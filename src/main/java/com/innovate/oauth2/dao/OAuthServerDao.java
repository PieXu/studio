package com.innovate.oauth2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.oauth2.model.OAuthClient;

@Repository
public interface OAuthServerDao{

	/**
	 * 
	 * @param param
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+OAuthClient.TAB_NAME+" where client_id = #{clientId}")
	public List<OAuthClient> getClientByClientId(String clientId);

	/**
	 * 
	 * @param clientSecret
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+OAuthClient.TAB_NAME+" where client_secret = #{clientSecret}")
	public List<OAuthClient> getClientByClientSecret(String clientSecret);


}
