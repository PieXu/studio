package com.innovate.bizz.goods.dao;

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
import com.innovate.bizz.goods.model.Goods;

@Repository
public interface GoodsDao {

	/**
	 * 主键删除
	 * @param roleId
	 */
	@Delete("delete from " +Goods.TAB_NAME + " where id = #{id}")
	public void deleteById(String id);

	/**
	 * 保存信息
	 * @param resRole
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into " +Goods.TAB_NAME + " (#{goods}) ")
	public void saveGoods(Goods goods);

	/**
	 * 主键查询
	 * @param roleId
	 * @param resId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " +Goods.TAB_NAME+ " where id = #{id}")
	public Goods getGoodsById(String id);

	/**
	 * 列表查询
	 * @param breaing
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+Goods.TAB_NAME+" (#{goods})")
	public Page<Goods> listGoods(Goods goods);
	
	
	/**
	 * 更新
	 * @param breaing
	 */
	@Update("UPDATE "+ Goods.TAB_NAME +" (#{goods}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateGoods(Goods goods);
	
}
