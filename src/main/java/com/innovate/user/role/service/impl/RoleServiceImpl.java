package com.innovate.user.role.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.user.role.dao.RoleDao;
import com.innovate.user.role.model.Role;
import com.innovate.user.role.service.IRoleService;
import com.innovate.user.user.dao.UserRoleDao;
import com.innovate.user.user.model.UserRole;
import com.innovate.util.IdUtil;

@Service("user.RoleServiceImpl")
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	
	/**
	 * 角色分页查询
	 */
	@Override
	public Page<Role> pageRole(Role role) {
		return roleDao.pageRole(role);
	}

	@Override
	public Role getRoleById(String id) {
		if(StringUtils.isNotBlank(id))
			return roleDao.getRoleById(id);
		return null;
	}

	@Override
	public int checkUserRole(String id) {
		if(StringUtils.isNotBlank(id)){
			return roleDao.checkUserRole(id);
		}
		return 0;
	}

	@Override
	public void deleteRole(String id) {
		if(StringUtils.isNotBlank(id)){
			roleDao.deleteRole(id);
		}
	}

	@Override
	public void saveOrUpdateRole(Role role) {
		String id = role.getId();
		if(StringUtils.isEmpty(id)){
			role.setId(IdUtil.getUUID());
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			roleDao.saveRole(role);
		}else{
			role.setUpdateTime(new Date());
			roleDao.updateRole(role);
		}
	}

	@Override
	public List<Role> getRoleList() {
		return roleDao.getRoleList();
	}

	@Override
	public List<String> getRoleByUserId(String userId) {
		if(StringUtils.isNotBlank(userId)){
			return roleDao.getRoleByUserId(userId);
		}
		return null;
	}

	@Override
	public void delRoleByUserId(String userId) {
		if(StringUtils.isNotBlank(userId)){
			userRoleDao.delRoleByUserId(userId);
		}
	}

	@Override
	public void deleteNotContaintRoleByUserId(String userId, List<String> roleIds) {
		if(StringUtils.isNotBlank(userId)){
			userRoleDao.deleteNotContaintRoleByUserId(userId,roleIds);
		}
	}

	@Override
	public int getUserRole(String userId, String roleId) {
		List<UserRole> userRoleList = userRoleDao.getUserRole(userId,roleId);
		return null == userRoleList? 0 : userRoleList.size();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		userRoleDao.saveUserRole(userRole);
	}

}
