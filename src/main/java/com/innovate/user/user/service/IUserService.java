package com.innovate.user.user.service;

import com.github.pagehelper.Page;
import com.innovate.user.user.model.User;

/**
 * @time: 2017年6月20日 下午2:54:25
 * @author IvanHsu 
 */
public interface IUserService {
	
	/**
	 * 根据登录名查询
	 * @param loginName
	 * @return
	 * User
	 * 2017年6月19日
	 */
	public	User queryUserByLoginName(String loginName);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public Page<User> pageUser(User user);

	/**
	 * 主键查找
	 * @param id
	 * @return
	 */
	public User getUserById(String id);

	/**
	 * 保存或者更新
	 * @param user
	 */
	public void saveOrUpdateUser(User user);

	/**
	 * 逻辑删除，更改删除状态
	 * @param id
	 */
	public void logicDeleteById(String id);

	/**
	 * 保存用户对应的角色
	 * @param userId
	 * @param roleIds
	 */
	public void saveUserRole(String userId, String[] roleIds);
}
