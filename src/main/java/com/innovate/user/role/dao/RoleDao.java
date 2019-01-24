package com.innovate.user.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.user.role.model.Role;

@Repository("user.RoleDao")
public interface RoleDao {
	
	/**
	 * 角色信息分页查询
	 * @param role
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+Role.TAB_NAME+" (#{role}) order by update_time desc ")
	public Page<Role> pageRole(Role role);

	/**
	 * 主键查找
	 * @param id
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+Role.TAB_NAME+" WHERE id = #{id} ")
	public Role getRoleById(String id);
	
	/**
	 * 查询管理表信息
	 * @param id
	 * @return
	 */
	@Select("SELECT count(*) FROM t_user_role WHERE role_id = #{id} ")
	public int checkUserRole(String id);

	/**
	 * 删除role 物理删除
	 * @param id
	 */
	@Delete("delete from " +Role.TAB_NAME + " where id = #{id}")
	public void deleteRole(String id);

	/**
	 * 保存
	 * @param role
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("INSERT INTO "+ Role.TAB_NAME +" (#{role})")
	public void saveRole(Role role);

	/**
	 * 更新
	 * @param role
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("UPDATE "+ Role.TAB_NAME +" (#{role}) WHERE ID = #{id}")
	public void updateRole(Role role);

	/**
	 * 
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+Role.TAB_NAME+" order by update_time desc ")
	public List<Role> getRoleList();

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@Select("SELECT role_id FROM t_user_role WHERE user_id = #{userId} ")
	public List<String> getRoleByUserId(String userId);

	
}
