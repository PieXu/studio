package com.innovate.user.role.service;

import java.util.List;

import com.innovate.basic.base.IBaseService;
import com.innovate.sys.resource.model.Resource;
import com.innovate.user.role.model.Role;

public interface IResRoleService extends IBaseService{

	/**
	 * 保存角色授权信息
	 * @param roleId
	 * @param ids
	 */
	public void saveRoleRes(String roleId, String ids);

	/**
	 * 检查角色是否授权改菜单
	 * @param id
	 * @param id2
	 * @return
	 */
	public boolean checkRoleHasRes(String id, String id2);

	/**
	 * 获取角色列表下的菜单信息
	 * @param roleList
	 * @return
	 */
	public List<Resource> getResByRoleList(List<String> roleList);
	
	/**
	 * 获取角色下的一级菜单的集合
	 * @param roleList
	 * @return
	 */
	public List<Resource> getRootResByRoleList(List<String> roleList);
	
	/**
	 * 获取菜单下有几个角色配置
	 * @param resId
	 * @return
	 */
	public List<Role> getRoleByResId(String resId);
}
