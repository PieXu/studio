package com.innovate.sys.security.dao;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.user.user.model.User;

/**
 * @time: 2017年6月19日 下午5:57:52
 * @author IvanHsu 
 */
@Repository("security.loginDao")
public interface LoginDao {

	/**
	 * @param loginName
	 * @return
	 * User
	 * 2017年6月19日
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + User.TAB_NAME + " where login_name = #{loginName} ")
	public User queryUserByLoginName(String loginName);
	
}
