package com.innovate.sys.security.service;

import java.util.List;

import com.innovate.basic.base.IBaseService;

/**
 * @author IvanHsu
 * @2018年4月2日 上午10:13:40
 */
public interface ISecurityService extends IBaseService{

	/**
	 * 在线用户列表
	 * @return
	 */
	public List<?> loadAllOnlineUser();

	/**
	 * 踢出在线用户
	 * @param sessionId
	 */
	public String kickoutUser(String sessionId);

}
