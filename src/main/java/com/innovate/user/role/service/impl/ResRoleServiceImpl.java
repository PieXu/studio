package com.innovate.user.role.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.sys.resource.model.Resource;
import com.innovate.user.role.dao.ResRoleDao;
import com.innovate.user.role.model.ResRole;
import com.innovate.user.role.model.Role;
import com.innovate.user.role.service.IResRoleService;
import com.innovate.util.IdUtil;

@Service("user.service.ResRoleServiceImpl")
public class ResRoleServiceImpl implements IResRoleService{

	@Autowired
	private ResRoleDao resRoleDao;

	/**
	 * 角色授权信息保存
	 */
	@Override
	public void saveRoleRes(String roleId, String ids) {
		if(StringUtils.isNotBlank(roleId)){
			/*
			 *1.按照对应关系先删除
			 *2.保存现有的对应关系 
			 */
			resRoleDao.deleteByRoleId(roleId);
			if(StringUtils.isNotBlank(ids)){
				String[] resIdArray = ids.split(",");
				for(String resId : resIdArray){
					if(StringUtils.isNotBlank(resId)){
						ResRole resRole = new ResRole(IdUtil.getUUID(),resId,roleId);
						resRoleDao.saveResRole(resRole);
					}
				}
			}
		}
	}

	@Override
	public boolean checkRoleHasRes(String roleId, String resId) {
		if(StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(resId)){
			ResRole resRole = resRoleDao.getRoleResByResRole(roleId,resId);
			return null == resRole ? false : true;
		}
		return false;
	}

	/**
	 * 角色列表下的菜单信息
	 */
	@Override
	public List<Resource> getResByRoleList(List<String> roleList) {
		if(null!=roleList && !roleList.isEmpty()){
			return resRoleDao.getResByRoleList(roleList);
		}
		return null;
	}
	
	@Override
	public List<Resource> getRootResByRoleList(List<String> roleList) {
		if(null!=roleList && !roleList.isEmpty()){
			return resRoleDao.getRootResByRoleList(roleList);
		}
		return null;
	}
	

	@Override
	public List<Role> getRoleByResId(String resId) {
		if(StringUtils.isNotBlank(resId)){
			return resRoleDao.getRoleByResId(resId);
		}
		return null;
	}

}