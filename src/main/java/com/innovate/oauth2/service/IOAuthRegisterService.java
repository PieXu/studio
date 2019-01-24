package com.innovate.oauth2.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.oauth2.model.OAuthClient;

/**
 * 客户端注册业务类
 * @author IvanHsu
 * @2018年8月20日 下午4:55:17
 */
public interface IOAuthRegisterService extends IBaseService{

	/**
	 * 分页查询列表
	 * @param client
	 * @return
	 */
	public Page<OAuthClient> listRegisterList(OAuthClient client);

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public OAuthClient getById(String id);

	/**
	 * 
	 * @param client
	 */
	public void updateOAuthClient(OAuthClient client);

	/**
	 * 
	 * @param client
	 */
	public void saveOAuthClient(OAuthClient client);

}
