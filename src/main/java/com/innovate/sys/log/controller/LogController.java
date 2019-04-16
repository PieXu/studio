package com.innovate.sys.log.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.log.model.AuditLog;
import com.innovate.sys.log.model.VisitLog;
import com.innovate.sys.log.service.IAuditLogService;
import com.innovate.sys.log.service.IVisitLogService;
import com.innovate.util.CommonCons;

/**
 * 日志管理
 * @author IvanHsu
 * @2018年3月31日 下午4:01:13
 */
@Controller("innovate.log.controller.LogController")
public class LogController extends BaseController{

	@Autowired
	private IVisitLogService logService;
	@Autowired
	private IAuditLogService auditLogService;
	/**
	 * 访问日志的查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="log/visitLogList")
	public String visitLogList(HttpServletRequest request,Model model,VisitLog log,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<VisitLog> page = logService.pageVisitLog(log);
		model.addAttribute("page",page);
		model.addAttribute("log",log);
		return "log/visitLogList";
	}
	
	/**
	 * 访问日志的查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="log/auditLogList")
	public String auditLogList(HttpServletRequest request,Model model,AuditLog log,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<AuditLog> page = auditLogService.pageAuditLog(log);
		model.addAttribute("page",page);
		model.addAttribute("log",log);
		return "log/auditLogList";
	}
	
}
