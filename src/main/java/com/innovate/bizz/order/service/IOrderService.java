package com.innovate.bizz.order.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.bizz.order.model.Order;

public interface IOrderService extends IBaseService{

	/**
	 * 分页列表查询
	 * @param order
	 * @return
	 */
	public Page<Order> listOrder(Order order);

	/**
	 * 更新状态
	 * @param order
	 */
	public void updateOrder(Order order);

}
