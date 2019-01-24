package com.innovate.user.user.dao;

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
import com.innovate.user.user.model.UserRole;

@Repository("user.dao.UserRoleDao")
public interface UserRoleDao {

	/**
	 * 删除用户下的对应关系
	 * @param userId
	 */
	@Delete("delete from "+UserRole.TAB_NAME+" where user_id = #{userId}")
	public void delRoleByUserId(String userId);

	/**
	 * 部分删除解除关系的数据
	 * @param userId
	 * @param roleIds
	 */
	@Lang(SimpleInLangDriver.class)
	@Delete("delete from "+UserRole.TAB_NAME+" where user_id = #{userId} and role_id not in (#{roleList})")
	public void deleteNotContaintRoleByUserId( @Param("userId")String userId, @Param("roleList")List<String> roleList);

	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+UserRole.TAB_NAME+" where user_id = #{userId} and role_id = #{roleId} ")
	public List<UserRole> getUserRole(@Param("userId")String userId, @Param("roleId")String roleId);

	/**
	 * 保存
	 * @param userRole
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " +UserRole.TAB_NAME+ " (#{userRole})")
	public void saveUserRole(UserRole userRole);

}
