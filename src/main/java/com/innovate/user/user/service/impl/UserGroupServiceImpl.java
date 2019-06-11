package com.innovate.user.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.innovate.user.user.dao.UserGroupDao;
import com.innovate.user.user.model.UserGroup;
import com.innovate.user.user.service.IUserGroupService;
import com.innovate.util.IdUtil;

@Service("com.innovate.user.user.service.impl.UserGroupServiceImpl")
public class UserGroupServiceImpl implements IUserGroupService {

	@Autowired
	private UserGroupDao userGroupDao;
	
	@Override
	public Page<UserGroup> pageUserGroup(UserGroup group) {
		return userGroupDao.pageUserGroup(group);
	}

	@Override
	public UserGroup getUserGroupById(String id) {
		if(StringUtils.hasText(id)){
			return userGroupDao.getUserGroupById(id);
		}
		return null;
	}

	@Override
	public void saveOrUpdateUserGroup(UserGroup userGroup) {
		String id = userGroup.getId();
		if(StringUtils.isEmpty(id)){
			userGroup.setId(IdUtil.getUUID());
			userGroup.setCreateTime(new Date());
			userGroup.setUpdateTime(new Date());
			userGroupDao.saveUserGroup(userGroup);
		}else{
			userGroup.setUpdateTime(new Date());
			userGroupDao.updateUserGroup(userGroup);
		}
	}

	@Override
	public void logicDeleteById(String id) {
		if(StringUtils.hasText(id)){
			userGroupDao.logicDeleteById(id);
		}
	}

	
}
