package com.innovate.user.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.innovate.basic.driver.SimpleInLangDriver;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.sys.resource.model.Resource;
import com.innovate.user.role.model.ResRole;
import com.innovate.user.role.model.Role;

@Repository("user.dao.ResRoleDao")
public interface ResRoleDao {

	/**
	 * 删除角色下的所有菜单
	 * @param roleId
	 */
	@Delete("delete from " +ResRole.TAB_NAME + " where role_id = #{roleId}")
	public void deleteByRoleId(String roleId);

	/**
	 * 保存角色授权菜单
	 * @param resRole
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " +ResRole.TAB_NAME + " (#{resRole}) ")
	public void saveResRole(ResRole resRole);

	/**
	 * 
	 * @param roleId
	 * @param resId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " +ResRole.TAB_NAME+ " where role_id=#{roleId} and res_id = #{resId}")
	public ResRole getRoleResByResRole(@Param("roleId")String roleId,@Param("resId") String resId);

	/**
	 * 角色列表下的菜单信息
	 * @param roleList
	 * @return
	 */
	@Lang(SimpleInLangDriver.class)
	@Select("select * from " +Resource.TABLE_NAME+ " where id in (select distinct res_id from "+ResRole.TAB_NAME+" where role_id in (#{roleList}) ) order by seq_num ")
	public List<Resource> getResByRoleList(@Param("roleList")List<String> roleList);

	/**
	 * 角色列表下根一级菜单信息
	 * @param roleList
	 * @return
	 */
	@Lang(SimpleInLangDriver.class)
	@Select("select * from " +Resource.TABLE_NAME+ " where 1=1 and ( parent is null or parent = '' ) and id in (select distinct res_id from "+ResRole.TAB_NAME+" where role_id in (#{roleList}) ) order by seq_num ")
	public List<Resource> getRootResByRoleList(@Param("roleList")List<String> roleList);

	/**
	 * 根据菜单获取配置的角色菜单
	 * @param resId
	 * @return
	 */
	@Select("select * from " +Role.TAB_NAME+ " where id in (select distinct role_id from "+ResRole.TAB_NAME+" where res_id =#{resId} )")
	public List<Role> getRoleByResId(@Param("resId")String resId);
	
}
