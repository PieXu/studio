package com.innovate.user.user.service;

import com.github.pagehelper.Page;
import com.innovate.user.user.model.UserGroup;

/**
 * 用戶組管理
 */
public interface IUserGroupService {

	/**
	 * 分頁查詢
	 * @param group
	 * @return
	 */
	public Page<UserGroup> pageUserGroup(UserGroup group);
	
	/**
	 * 主键查找
	 * @param id
	 * @return
	 */
	public UserGroup getUserGroupById(String id);

	/**
	 * 保存或者更新
	 */
	public void saveOrUpdateUserGroup(UserGroup userGroup);

	/**
	 * 逻辑删除，更改删除状态
	 * @param id
	 */
	public void logicDeleteById(String id);

}
