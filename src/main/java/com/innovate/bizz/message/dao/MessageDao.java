package com.innovate.bizz.message.dao;

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
import com.innovate.bizz.message.model.Message;

@Repository
public interface MessageDao {

	/**
	 * 主键删除
	 * @param roleId
	 */
	@Delete("delete from " +Message.TAB_NAME + " where id = #{id}")
	public void deleteById(String id);

	/**
	 * 保存信息
	 * @param resRole
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " +Message.TAB_NAME + " (#{message}) ")
	public void saveMessage(Message message);

	/**
	 * 主键查询
	 * @param roleId
	 * @param resId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " +Message.TAB_NAME+ " where id = #{id}")
	public Message getMessageById(String id);

	/**
	 * 列表查询
	 * @param breaing
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+Message.TAB_NAME+" (#{message})")
	public Page<Message> listMessage(Message message);
	
	
	/**
	 * 更新
	 * @param breaing
	 */
	@Update("UPDATE "+ Message.TAB_NAME +" (#{message}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateMessage(Message message);


	
}
