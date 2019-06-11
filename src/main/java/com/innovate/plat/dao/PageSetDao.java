package com.innovate.plat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.plat.model.PageDefine;
import com.innovate.plat.model.PageSet;

@Repository
public interface PageSetDao {

	/**
	 * 
	* <p>Title: savePageSet</p>
	* <p>Description: </p>
	* @param pageset
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into "+PageSet.TAB_NAME +" (#{pageset})")
	public void savePageSet(PageSet pageset);

	/**
	 * 
	* <p>Title: updatePageSet</p>
	* <p>Description: </p>
	* @param pageset
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("update "+ PageSet.TAB_NAME +" (#{pageset}) WHERE ID = #{id}")
	public void updatePageSet(PageSet pageset);

	/**
	 * 
	* <p>Title: getByPageId</p>
	* <p>Description: </p>
	* @param pageId
	* @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + PageSet.TAB_NAME +" where page_id=#{pageId} order by seq asc")
	public List<PageSet> getByPageId(String pageId);

}
