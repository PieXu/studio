package com.innovate.bizz.order.dao;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.bizz.order.model.Order;

@Repository
public interface OrderDao{

	/**
	 * 列表查询
	 * @param order
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+Order.TAB_NAME+" (#{order}) order by create_time desc")
	public Page<Order> listOrder(Order order);
	
	
	/**
	 * 更新
	 * @param order
	 */
	@Update("UPDATE "+ Order.TAB_NAME +" (#{order}) WHERE ID = #{id}")
	@Lang(SimpleUpdateLangDriver.class)
	public void updateOrder(Order order);
}
