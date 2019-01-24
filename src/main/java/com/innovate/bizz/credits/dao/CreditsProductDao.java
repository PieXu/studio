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
import com.innovate.bizz.credits.model.CreditsProduct;

@Repository
public interface CreditsProductDao {

	/**
	 * 主键删除
	 * @param roleId
	 */
	@Delete("delete from " +CreditsProduct.TAB_NAME + " where id = #{id}")
	public void deleteById(String id);

	/**
	 * 保存信息
	 * @param resRole
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " +CreditsProduct.TAB_NAME + " (#{product}) ")
	public void saveCreditsProduct(CreditsProduct product);

	/**
	 * 主键查询
	 * @param roleId
	 * @param resId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " +CreditsProduct.TAB_NAME+ " where id = #{id}")
	public CreditsProduct getCreditsProductById(String id);

	/**
	 * 列表查询
	 * @param breaing
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+CreditsProduct.TAB_NAME+" (#{product})")
	public Page<CreditsProduct> listCreditsProduct(CreditsProduct product);
	
	
	/**
	 * 更新
	 * @param breaing
	 */
	@Update("UPDATE "+ CreditsProduct.TAB_NAME +" (#{product}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateGoods(CreditsProduct product);
	
}
