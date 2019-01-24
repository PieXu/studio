/**
 * @name:UserDao.java
 * @package:com.xu.user.dao
 * @time: 2017年6月20日 下午2:55:09
 * @author IvanHsu 
 */
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
import com.innovate.user.user.model.User;
import com.innovate.util.CommonCons;

/**
 * @time: 2017年6月20日 下午2:55:09
 * @author IvanHsu 
 */
@Repository("user.UserDao")
@CacheNamespace(implementation=org.mybatis.caches.ehcache.EhcacheCache.class)
public interface UserDao {
	
	/**
	 * 分页条件查询
	 * @param user
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+User.TAB_NAME+" (#{user}) and  login_name !='admin' order by update_time desc ")
	@Options(useCache=true,flushCache = FlushCachePolicy.FALSE,timeout=100000)
	public Page<User> pageUser(User user);
	
	/**
	 * 按主键查找
	 * @param id
	 * @return
	 * User
	 * 2017年7月6日
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+User.TAB_NAME+" where id = #{id} ")
	public User getUserById(String id);
	
	/**
	 * 保存用户信息
	 * @param user
	 * void
	 * 2017年6月20日
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("INSERT INTO "+ User.TAB_NAME +" (#{user})")
	public void saveUser(User user);

	
	/**
	 * 更新User
	 * @param user
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("UPDATE "+ User.TAB_NAME +" (#{user}) WHERE ID = #{id}")
	public void updateUser(User user);
	
	/**
	 * @param loginName
	 * @return
	 * User
	 * 2017年6月19日
	 */
	@Select("select * from "+ User.TAB_NAME +" where login_name = #{loginName} and del_flag = '"+CommonCons.STATUS_NORMAL+"'")
	public	User queryUserByLoginName(String loginName);

	/**
	 * 逻辑删除用户，修改状态
	 * @param id
	 * @return
	 */
	@Update("UPDATE "+ User.TAB_NAME +" set del_flag  = '"+CommonCons.STATUS_DELETE+"'  WHERE ID = #{id}")
	public void logicDeleteById(String id);

}
