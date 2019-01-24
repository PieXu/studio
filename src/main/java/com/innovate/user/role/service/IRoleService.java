package com.innovate.user.role.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.innovate.user.role.model.Role;
import com.innovate.user.user.model.UserRole;

public interface IRoleService {

	/**
	 * 分页查询
	 * @param role
	 * @return
	 */
	public Page<Role> pageRole(Role role);

	/**
	 * 主键查找
	 * @param id
	 * @return
	 */
	public Role getRoleById(String id);

	/**
	 * 查询管理表信息
	 * @param id
	 * @return
	 */
	public int checkUserRole(String id);

	/**
	 * 删除role 物理删除
	 * @param id
	 */
	public void deleteRole(String id);

	/**
	 * 更新或保存
	 * @param role
	 */
	public void saveOrUpdateRole(Role role);

	/**
	 * 所有角色列表
	 * @return
	 */
	public List<Role> getRoleList();

	/**
	 * 用户角色的idlist
	 * @param id
	 * @return
	 */
	public List<String> getRoleByUserId(String id);

	/**
	 * 
	 * @param userId
	 */
	public void delRoleByUserId(String userId);

	/**
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void deleteNotContaintRoleByUserId(String userId, List<String> roleIds);

	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getUserRole(String userId, String roleId);

	/**
	 * 
	 * @param userRole
	 */
	public void saveUserRole(UserRole userRole);

}
