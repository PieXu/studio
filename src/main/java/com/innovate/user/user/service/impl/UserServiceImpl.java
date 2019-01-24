package com.innovate.user.user.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.innovate.user.role.service.IRoleService;
import com.innovate.user.user.dao.UserDao;
import com.innovate.user.user.model.User;
import com.innovate.user.user.model.UserRole;
import com.innovate.user.user.service.IUserService;
import com.innovate.util.IdUtil;

/**
 * @time: 2017年6月20日 下午2:54:44
 * @author IvanHsu 
 */
@Service("user.service.UserServiceImpl")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private IRoleService roleSrvice;

	/* (non-Javadoc)
	 * @see com.xu.user.service.IUserService#saveUser(com.xu.user.model.User)
	 */
	@Override
	public void saveOrUpdateUser(User user)
	{
		String id = user.getId();
		if(StringUtils.isEmpty(id)){
			user.setId(IdUtil.getUUID());
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			userDao.saveUser(user);
		}else{
			user.setUpdateTime(new Date());
			userDao.updateUser(user);
		}
	}

	@Override
	public User queryUserByLoginName(String loginName) {
		if(StringUtils.hasText(loginName))
			return userDao.queryUserByLoginName(loginName);
		return null;
	}

	@Override
	public Page<User> pageUser(User user) {
		return userDao.pageUser(user);
	}

	@Override
	public User getUserById(String id) {
		if(StringUtils.hasText(id))
			return userDao.getUserById(id);
		return null;
	}

	@Override
	public void logicDeleteById(String id) {
		if(StringUtils.hasText(id)){
			userDao.logicDeleteById(id);
		}
	}

	@Override
	public void saveUserRole(String userId, String[] roleIds) {
		if(StringUtils.hasText(userId)){
			// 如果角色为空，清空所有的角色对应关系
			if(ArrayUtils.isEmpty(roleIds)){
				roleSrvice.delRoleByUserId(userId);
			}else{//如果角色不为空， 先删除不包含的用户角色，然后在插入
				 @SuppressWarnings("unchecked")
				List<String>  roleList = CollectionUtils.arrayToList(roleIds);
				roleSrvice.deleteNotContaintRoleByUserId(userId,roleList);
				for(String roleId : roleIds){
					if(roleSrvice.getUserRole(userId,roleId) == 0 ){
						UserRole userRole = new UserRole();
						userRole.setId(IdUtil.getUUID());
						userRole.setUserId(userId);
						userRole.setRoleId(roleId);
						userRole.setCreateTime(new Date());
						userRole.setUpdateTime(new Date());
						roleSrvice.saveUserRole(userRole);
					}
				}
			}
		}
		
	}
	
}
