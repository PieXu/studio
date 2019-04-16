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
import com.innovate.sys.resource.model.Opt;
import com.innovate.user.role.model.RoleResOpt;

@Repository("sys.dao.RoleResOptDao")
public interface RoleResOptDao {

	/**
	 * 按照菜单删除
	 * @param resId
	 */
	@Delete("delete from " + RoleResOpt.TAB_NAME + " where res_id = #{resId}")
	public void deleteRoleOptByRes(String resId);

	
	/**
	 * 保存信息
	 * @param roleResOpt
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " + RoleResOpt.TAB_NAME + " (#{roleResOpt})")
	public void saveRoleResOpt(RoleResOpt roleResOpt);


	/**
	 *  根据菜单查找配置的角色操作信息
	 * @param menuId
	 * @return
	 */
	@Select("select * from " + RoleResOpt.TAB_NAME + " where res_id = #{menuId}")
	public List<RoleResOpt> getRoleOptByRes(String menuId);


	/**
	 * 
	 * @param resId
	 * @param roleIdList
	 * @return
	 */
	@Lang(SimpleInLangDriver.class)
	@Select("select * from " + Opt.TAB_NAME +" where id in (select distinct opt_id from "+RoleResOpt.TAB_NAME+" where res_id =#{resId} and role_id in (#{roleIdList}))")
	public List<Opt> getOptsByResRole(@Param("resId")String resId, @Param("roleIdList")List<String> roleIdList);

	/**
	 * 
	 * @param resId
	 * @return
	 */
	@Lang(SimpleInLangDriver.class)
	@Select("select * from " + Opt.TAB_NAME +" where id in (select distinct opt_id from "+RoleResOpt.TAB_NAME+" where res_id =#{resId})")
	public List<Opt> getOptsByRes(String resId);

}
