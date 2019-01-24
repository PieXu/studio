package com.innovate.oauth2.dao;

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
import com.innovate.oauth2.model.OAuthClient;

@Repository
public interface OAuthRegDao{

	/**
	 * 列表查询
	 * @param breaing
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+OAuthClient.TAB_NAME+" (#{client})")
	public Page<OAuthClient> listRegisterList(OAuthClient client);
	
	/**
	 * 按主键删除
	 * @param id
	 */
	@Delete("DELETE FROM "+ OAuthClient.TAB_NAME +" WHERE id = #{id}")
	public void deleteById(String id);
	
	/**
	 * 按主键查找
	 * @param id
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+OAuthClient.TAB_NAME+" WHERE ID = #{id}")
	public OAuthClient getById(String id);
	
	/**
	 * 更新
	 * @param breaing
	 */
	@Update("UPDATE "+ OAuthClient.TAB_NAME +" (#{client}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateOAuthClient(OAuthClient client);

	/**
	 * 保存
	 * @param breaing
	 */
	@Insert("INSERT INTO "+ OAuthClient.TAB_NAME +" (#{client})")
	@Lang(SimpleInsertLangDriver.class)
	public void saveOAuthClient(OAuthClient client);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+OAuthClient.TAB_NAME+" (#{param})")
	public List<OAuthClient> getOAuthClient(OAuthClient param);


}
