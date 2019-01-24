package com.innovate.sys.security.service;

import com.innovate.user.user.model.User;

/**
 * @time: 2017年6月19日 下午5:33:03
 * @author IvanHsu 
 */
public interface ILoginService {

	/**
	 * 根据登录名查询
	 * @param loginName
	 * @return
	 * User
	 * 2017年6月19日
	 */
	User queryUserByLoginName(String loginName);
	
	
	
}
