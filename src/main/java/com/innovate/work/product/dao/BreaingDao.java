package com.innovate.work.product.dao;

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
import com.innovate.work.product.model.Bearing;

@Repository("product.dao.BreaingDao")
public interface BreaingDao {
	
	static final String TAB_NAME = "t_p_breaing";
	
	/**
	 * 列表查询
	 * @param breaing
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+TAB_NAME+" (#{breaing})")
	public Page<Bearing> listBreaing(Bearing breaing);

	/**
	 * 按主键删除
	 * @param id
	 */
	@Delete("DELETE FROM "+ TAB_NAME +" WHERE id = #{id}")
	public void deleteById(String id);

	/**
	 * 按主键查找
	 * @param id
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+TAB_NAME+" WHERE ID = #{id}")
	public Bearing getById(String id);
	
	/**
	 * 更新
	 * @param breaing
	 */
	@Update("UPDATE "+ TAB_NAME +" (#{breaing}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateBreaing(Bearing breaing);

	/**
	 * 保存
	 * @param breaing
	 */
	@Insert("INSERT INTO "+ TAB_NAME +" (#{breaing})")
	@Lang(SimpleInsertLangDriver.class)
	public void saveBreaing(Bearing breaing);

}
