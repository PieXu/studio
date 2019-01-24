package com.innovate.bizz.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.bizz.goods.model.Goods;
import com.innovate.bizz.order.model.Order;
import com.innovate.bizz.order.service.IOrderService;
import com.innovate.bizz.util.BizzUtils;
import com.innovate.util.CommonCons;
import com.innovate.util.ResultObject;

/**
 * 订单管理后台模块
 * @author IvanHsu
 * @2018年7月9日 下午3:08:32
 */
@Controller
public class OrderController extends BaseController{

	@Autowired
	private IOrderService orderService;
	
	/**      
	 * 订单列表
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="order/listOrder")
	public String listOrder(HttpServletRequest request,Model model,Order order,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<Order> page = orderService.listOrder(order);
		Map<String, String> orderStatus = BizzUtils.getOrderStatus();
		model.addAttribute("page",page);
		model.addAttribute("order", order);
		model.addAttribute("orderStatus", orderStatus);
		return "order/list/orderList";
	}
	
	/**
	 * 状态变换
	 * @param request
	 * @param model
	 * @param id
	 * @param isHot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="order/changeStatus")
	public ResultObject changeIsHot(HttpServletRequest request,Model model,String id,String status)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				Order order = new Order();
				order.setId(id);
				order.setOrderStatus(status);
				orderService.updateOrder(order);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("操作成功");
			}catch (Exception e) {
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
}
