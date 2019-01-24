package com.innovate.oauth2.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.innovate.oauth2.dao.OAuthServerDao;
import com.innovate.oauth2.model.OAuthClient;
import com.innovate.oauth2.service.IOAuthService;

@Service
public class OAuthServiceImpl implements IOAuthService{

	private Cache cache;
	
	@Autowired
	private OAuthServerDao authServerDao;	
	
	public OAuthServiceImpl(CacheManager cacheManager){
		this.cache = cacheManager.getCache("code-cache");
	}
	
	@Override
	public OAuthClient getOAuthClientByClientId(String clientId) {
		OAuthClient client = null;
		if(StringUtils.isNotBlank(clientId)){
			List<OAuthClient> clientList = authServerDao.getClientByClientId(clientId);
			if(!clientList.isEmpty()){
				client = clientList.get(0);//默认去第一个，在新增的时候做判重限制，保证clientID唯一
			}
		}
		return client;
	}

	@Override
	public void addAuthCode(String authCode, String username) {
		cache.put(authCode, username);
	}

	@Override
	public void addAccessToken(String accessToken, String username) {
		cache.put(accessToken, username);
	}

	@Override
	public boolean checkAuthCode(String authCode) {
		return null != cache.get(authCode);
	}

	@Override
	public String getUsernameByAuthCode(String authCode) {
		return (String)cache.get(authCode).get();
	}

	@Override
	public boolean checkClientSecret(String clientSecret) {
		if(StringUtils.isNotBlank(clientSecret)){
			List<OAuthClient> clientList = authServerDao.getClientByClientSecret(clientSecret);
			return !clientList.isEmpty();
		}
		return false;
	}

	@Override
	public boolean checkAccessToken(String accessToken) {
		return cache.get(accessToken) != null;
	}

	@Override
	public String getUsernameByAccessToken(String accessToken) {
		return (String)cache.get(accessToken).get();
	}

}
