package com.innovate.plat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.plat.model.PageDefine;

@Repository
public interface PageDefineDao {

	/**
	* <p>Title: listPageDefine</p>
	* <p>Description: 查询列表 </p>
	* @param pageDefine
	* @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + PageDefine.TAB_NAME +" (#{pageDefine})")
	public Page<PageDefine> listPageDefine(PageDefine pageDefine);

	/**
	 * 
	* <p>Title: getById</p>
	* <p>Description: </p>
	* @param id
	* @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + PageDefine.TAB_NAME +" where id = #{id}")
	public PageDefine getById(String id);

	/**
	 * 
	* <p>Title: updatePageDefine</p>
	* <p>Description: </p>
	* @param pageDefine
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("update "+ PageDefine.TAB_NAME +" (#{pageDefine}) WHERE ID = #{id}")
	public void updatePageDefine(PageDefine pageDefine);

	/**
	 * 
	* <p>Title: savePageDefine</p>
	* <p>Description: </p>
	* @param pageDefine
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into "+PageDefine.TAB_NAME +" (#{pageDefine})")
	public void savePageDefine(PageDefine pageDefine);

	/**
	 * 
	* <p>Title: getPageDefineByCode</p>
	* <p>Description: </p>
	* @param code
	* @param delStatus
	* @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + PageDefine.TAB_NAME +" where page_code = #{code} and del_flag=#{delStatus}")
	public List<PageDefine> getPageDefineByCode(@Param("code") String code,@Param("delStatus") String delStatus);

}
