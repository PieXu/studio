package com.innovate.oauth2.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.oauth2.dao.OAuthRegDao;
import com.innovate.oauth2.model.OAuthClient;
import com.innovate.oauth2.service.IOAuthRegisterService;

/**
 * 
 * @author IvanHsu
 * @2018年8月20日 下午4:55:56
 */
@Service
public class OAuthRegisterServiceImpl implements IOAuthRegisterService{

	@Autowired
	private OAuthRegDao authRegDao;
	
	/**
	 * 分页查询
	 */
	@Override
	public Page<OAuthClient> listRegisterList(OAuthClient client) {
		return authRegDao.listRegisterList(client);
	}

	/**
	 * 删除 主键物理删除
	 */
	@Override
	public void deleteById(String id) {
		if(StringUtils.isNotBlank(id)){
			authRegDao.deleteById(id);
		}
	}

	/**
	 * 主键查找
	 */
	@Override
	public OAuthClient getById(String id) {
		if(StringUtils.isNotBlank(id)){
			return authRegDao.getById(id);
		}
		return null;
	}

	/**
	 * 更新
	 */
	@Override
	public void updateOAuthClient(OAuthClient client) {
		if(null!=client){
			authRegDao.updateOAuthClient(client);
		}
		
	}

	/**
	 * 保存
	 */
	@Override
	public void saveOAuthClient(OAuthClient client) {
		if(null!=client){
			authRegDao.saveOAuthClient(client);
		}
	}

}
