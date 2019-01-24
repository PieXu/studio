package com.innovate.bizz.credits.dao;

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
import com.innovate.bizz.credits.model.CreditsExchange;

@Repository
public interface CreditsExchangeDao {

	/**
	 * 主键删除
	 * @param roleId
	 */
	@Delete("delete from " +CreditsExchange.TAB_NAME + " where id = #{id}")
	public void deleteById(String id);

	/**
	 * 保存信息
	 * @param resRole
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " +CreditsExchange.TAB_NAME + " (#{exchange}) ")
	public void saveCreditsExchange(CreditsExchange exchange);

	/**
	 * 主键查询
	 * @param roleId
	 * @param resId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " +CreditsExchange.TAB_NAME+ " where id = #{id}")
	public CreditsExchange getCreditsExchangeById(String id);

	/**
	 * 列表查询
	 * @param breaing
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+CreditsExchange.TAB_NAME+" (#{exchange})")
	public Page<CreditsExchange> listCreditsExchange(CreditsExchange exchange);
	
	
	/**
	 * 更新
	 * @param breaing
	 */
	@Update("UPDATE "+ CreditsExchange.TAB_NAME +" (#{exchange}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateCreditsExchange(CreditsExchange exchange);
}
