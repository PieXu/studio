package com.innovate.bizz.shop.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.bizz.shop.model.Shop;

/**
 * 
* <p>Title: ShopDao</p>
* <p>Description: </p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月15日
 */
public interface ShopDao {

	/**
	 * 主键删除
	 * @param roleId
	 */
	@Delete("delete from " +Shop.TAB_NAME + " where id = #{id}")
	public void deleteById(String id);

	/**
	 * 保存信息
	 * @param resRole
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " +Shop.TAB_NAME + " (#{shop}) ")
	public void saveShop(Shop shop);

	/**
	 * 主键查询
	 * @param roleId
	 * @param resId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " +Shop.TAB_NAME+ " where id = #{id}")
	public Shop getShopById(String id);

	/**
	 * 列表查询
	 * @param breaing
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+Shop.TAB_NAME+" (#{shop})")
	public Page<Shop> listShop(Shop shop);
	
	
	/**
	 * 更新
	 * @param breaing
	 */
	@Update("UPDATE "+ Shop.TAB_NAME +" (#{shop}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateShop(Shop shop);
}
