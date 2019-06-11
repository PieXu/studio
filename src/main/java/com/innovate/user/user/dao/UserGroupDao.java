package com.innovate.user.user.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.user.user.model.UserGroup;
import com.innovate.util.CommonCons;

@Repository("user.UserGroupDao")
@CacheNamespace(implementation=org.mybatis.caches.ehcache.EhcacheCache.class)
public interface UserGroupDao {
	
	/**
	 * 分页条件查询
	 * @param user
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+UserGroup.TAB_NAME+" (#{userGroup}) order by update_time desc ")
	//@Options(useCache=true,flushCache = FlushCachePolicy.FALSE,timeout=100000)
	public Page<UserGroup> pageUserGroup(UserGroup userGroup);
	
	/**
	 * 按主键查找
	 * @param id
	 * @return
	 * User
	 * 2017年7月6日
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+UserGroup.TAB_NAME+" where id = #{id} ")
	public UserGroup getUserGroupById(String id);
	
	/**
	 * 2017年6月20日
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("INSERT INTO "+ UserGroup.TAB_NAME +" (#{userGroup})")
	public void saveUserGroup(UserGroup userGroup);

	
	/**
	 * 更新
	 * @param user
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("UPDATE "+ UserGroup.TAB_NAME +" (#{userGroup}) WHERE ID = #{id}")
	public void updateUserGroup(UserGroup userGroup);
	
	/**
	 * 逻辑删除用户，修改状态
	 * @param id
	 * @return
	 */
	@Update("UPDATE "+ UserGroup.TAB_NAME +" set del_flag  = '"+CommonCons.STATUS_DELETE+"'  WHERE ID = #{id}")
	public void logicDeleteById(String id);

}
