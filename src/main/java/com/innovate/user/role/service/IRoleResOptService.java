package com.innovate.user.role.service;

import java.util.List;

import com.innovate.basic.base.IBaseService;
import com.innovate.user.role.model.RoleResOpt;

/**
 * 
 * @author IvanHsu
 * @2018年3月30日 下午3:21:59
 */
public interface IRoleResOptService extends IBaseService{

	/**
	 * 保存授权设置的信息
	 * @param menuId
	 * @param roleOpts
	 */
	public void saveRoleResOpt(String menuId, String[] roleOpts);

	/**
	 * 根据菜单查找配置的角色操作信息
	 * @param menuId
	 * @return
	 */
	public List<RoleResOpt> getRoleOptByRes(String menuId);

	/**
	 * 获取菜单的配置的操作的信息
	 * @param id
	 * @param roleIdList
	 * @return
	 */
	public List<String> getOptCodeByRes(String resId);
	
	/**
	 * 获取授权的操作的code的列表
	 * @param id
	 * @param roleIdList
	 * @return
	 */
	public List<String> getOptCode(String id, List<String> roleIdList);

}
