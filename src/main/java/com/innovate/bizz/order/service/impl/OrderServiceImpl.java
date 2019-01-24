package com.innovate.bizz.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.bizz.order.dao.OrderDao;
import com.innovate.bizz.order.model.Order;
import com.innovate.bizz.order.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public Page<Order> listOrder(Order order) {
		return orderDao.listOrder(order);
	}

	@Override
	public void updateOrder(Order order) {
		if(null != order){
			orderDao.updateOrder(order);
		}
	}
}
