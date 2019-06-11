package com.innovate.user.role.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.user.role.dao.RoleResOptDao;
import com.innovate.user.role.model.RoleResOpt;
import com.innovate.user.role.service.IRoleResOptService;

/**
 * 
 * @author IvanHsu
 * @2018年3月30日 下午3:28:19
 */
@Service("sys.service.RoleResOptServiceImpl")
public class RoleResOptServiceImpl implements IRoleResOptService{

	@Autowired
	private RoleResOptDao roleResOptDao;
	
	/**
	 * 保存授权设置的信息
	 */
	@Override
	public void saveRoleResOpt(String resId, String[] roleOpts) {
		if(StringUtils.isNotBlank(resId) && null!=roleOpts && roleOpts.length>0){
			/*
			 * 步骤：
			 * 1： 先删除所有设置的信息
			 * 2：逐条分析保存
			 */
			roleResOptDao.deleteRoleOptByRes(resId);
			for(String roleOpt : roleOpts){
				// 第一个 为optId, 第二个为 roleId // 参考对照 页面的值
				String[] arr = roleOpt.split(",");
				//String roleId, String resId, String optId
				RoleResOpt roleResOpt = new RoleResOpt(arr[1],resId,arr[0]);
				roleResOptDao.saveRoleResOpt(roleResOpt);
			}
			
		}
	}

	/**
	 * 根据菜单查找配置的角色操作信息
	 */
	@Override
	public List<RoleResOpt> getRoleOptByRes(String menuId) {
		if(StringUtils.isNotBlank(menuId)){
			return roleResOptDao.getRoleOptByRes(menuId);
		}
		return null;
	}

	/**
	 * roleIdList
	 */
	@Override
	public List<Map<String,Object>> getOptsByResRole(String resId, List<String> roleIdList) {
		if(StringUtils.isNotBlank(resId) && null!=roleIdList && roleIdList.size()>0){
			return roleResOptDao.getOptsByResRole(resId,roleIdList);
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> getOptsByRes(String resId) {
		if(StringUtils.isNotBlank(resId)){
			return roleResOptDao.getOptsByRes(resId);
		}
		return null;
	}

}
