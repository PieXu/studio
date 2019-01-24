package com.innovate.bizz.message.controller;

import java.util.Date;
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
import com.innovate.bizz.message.model.Message;
import com.innovate.bizz.message.service.IMessageService;
import com.innovate.bizz.util.BizzUtils;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SessionUtils;

/**
 * 公告 动态等后台管理
 * @author IvanHsu
 * @2018年7月7日 上午10:18:29
 */
@Controller
public class MessageController extends BaseController{

	@Autowired
	private IMessageService messageService;
	
	
	/**      
	 * 信息列表查询
	 * @param request
	 * @param model
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="message/listMessage")
	public String listMessage(HttpServletRequest request,Model model,Message message,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<Message> page = messageService.listMessage(message);
		Map<String, String> messageType = BizzUtils.getNoticeType();
		model.addAttribute("page",page);
		model.addAttribute("message", message);
		model.addAttribute("messageType", messageType);
		return "message/list/messageList";
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"message/addMessage","message/editMessage"})
	public String modifyMessage(HttpServletRequest request,Model model,String id)
	{
		Message message = new Message();
		if(!StringUtils.isEmpty(id)){
			message =  messageService.getById(id);
		}
		model.addAttribute("message", message);
		//页面需要的数据字典
		model.addAttribute("messageType", BizzUtils.getNoticeType());
		return "message/nsm/messageEdit";
	}
	
	/**
	 * 保存产品
	 * @param request
	 * @param model
	 * @param Message
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="message/saveMessage")
	public ResultObject saveMessage(HttpServletRequest request,Model model, Message  message)
	{
		ResultObject result = new ResultObject();
		String id = message.getId();
		try{
			if(!StringUtils.isEmpty(id)){
				message.setUpdateTime(new Date());
				message.setOperator(SessionUtils.getCurrentUserId());
				messageService.updateMessage(message);
				result.setMessage("更新成功");
			}else{
				message.setCreateTime(new Date());
				message.setUpdateTime(new Date());
				message.setId(IdUtil.getUUID());
				message.setOperator(SessionUtils.getCurrentUserId());
				messageService.saveMessage(message);
				result.setMessage("添加成功");
			}
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		}catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(getClass(), "产品更新保存异常："+e.getMessage(), e);
		}
		return result;
	}
	
	
	/**
	 * 删除对象
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="message/deleteMessage")
	public ResultObject deleteMessage(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				messageService.deleteById(id);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("该新闻已删除");
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
