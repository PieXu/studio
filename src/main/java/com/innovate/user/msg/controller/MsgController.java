package com.innovate.user.msg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.dic.model.Dic;
import com.innovate.user.msg.model.Msg;
import com.innovate.user.msg.service.IMsgService;
import com.innovate.util.CommonCons;
import com.innovate.work.product.model.Bearing;

/**
 * 站内消息列表
 * @author IvanHsu
 * @2018年8月28日 下午2:59:03
 */
@Controller 
public class MsgController extends BaseController{
	
	@Autowired
	private IMsgService msgService;
	
	/**      
	 * 消息列表列表查询
	 * @param request
	 * @param model
	 * @param msg
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="msg/msgList")
	public String listMsg(HttpServletRequest request,Model model,Integer pageNum,Msg msg)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
//		Page<Bearing> page = msgService.pageMsg(msg);
//		model.addAttribute("page",page);
		model.addAttribute("msg", msg);
		return "msg/list/msgList";
	}
	
	
	
}
