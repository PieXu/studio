package com.innovate.sys.security.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovate.basic.base.BaseController;
import com.innovate.core.util.MessageUtils;
import com.innovate.core.util.TextMessage;
import com.innovate.sys.log.model.VisitLog;
import com.innovate.sys.resource.model.Resource;
import com.innovate.sys.resource.service.IResourceService;
import com.innovate.sys.security.service.ISecurityService;
import com.innovate.user.user.model.User;
import com.innovate.util.CommonCons;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SessionUtils;

/**
 * 
 * @author IvanHsu
 * @2018年3月30日 下午5:39:00
 */
@Controller("security.controller.SecurityController")
public class SecurityController extends BaseController{

	@Autowired
	private IResourceService resService;
	@Autowired
	private ISecurityService securityService;
     private SimpMessagingTemplate template;
    @Autowired  
    public SecurityController(SimpMessagingTemplate template) {  
        this.template = template;  
    }  
	 
	/**
	 * laod menu
	 * @param request
	 * @param resId
	 * @throws IOException 
	 */
	@RequestMapping("security/loadMenu")
	public String loadMenu(HttpServletRequest request,HttpServletResponse response,String resId) throws IOException
	{
		if(StringUtils.isNotBlank(resId)){
			Resource res = resService.getResourceById(resId);
			if( null != res){
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
				session.setAttribute("_currentMenu", res.getId());
				String pathStr = "";
				pathStr = navstationPath(res,pathStr);
				session.setAttribute("_navstation_path", pathStr);
				if(StringUtils.isNotBlank(res.getLink())){
					return  "redirect:/" + res.getLink();
				}
			}
		}
		return "/";
	}
	
	/**
	 * 消息链接跳转
	 * @param request
	 * @param resId
	 * @throws IOException 
	 */
	@RequestMapping("security/loadMenuByCode")
	public String logMsg(HttpServletRequest request,HttpServletResponse response,String msgCode) throws IOException
	{
		if(StringUtils.isNotBlank(msgCode)){
			Resource res = resService.getResourceByCode(msgCode);
			if( null != res){
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
				session.setAttribute("_currentMenu", res.getId());
				String pathStr = "";
				pathStr = navstationPath(res,pathStr);
				session.setAttribute("_navstation_path", pathStr);
				if(StringUtils.isNotBlank(res.getLink())){
					return  "redirect:/" + res.getLink();
				}
			}
		}
		return "/";
	}
	
	/**
	 * 一级菜单点击编号
	 * @param request
	 * @param response
	 * @param menuId
	 * @return
	 */
	@RequestMapping("security/changeRootMenu")
	public String changeRootMenu(HttpServletRequest request,HttpServletResponse response,String menuId)
	{
		SessionUtils.setSessionAttribute("_currentRoot", menuId);
		User loginUser = (User) SessionUtils.getSessionAttribute("loginUser");
		if(null!=loginUser){
			/*
			 * menuId 为空的时候 为点击的首页 跳转到main方法去
			 * menuId 不为空的时候， 则跳转到对应的第一个链接
			 */
			if(StringUtils.isNotBlank(menuId)){//menuId
				String resId = "";
				Resource rootMenu = resService.getResourceById(menuId);
				List<Resource> linkList  = this.getChildLink(rootMenu,loginUser.getIsSuperUser());
				if(null!=linkList && linkList.size()>0){
					//所点击的菜单
					if(null!=rootMenu)
					{
						SessionUtils.setSessionAttribute("linkList", linkList);
						resId = clickMenu(resId,linkList);
					}
					return "redirect:loadMenu.do?resId="+resId;
				}
			}
			return "redirect:/main.do";
		}
		return "redirect:/login.do";
	}
	
	/**
	 * 递归循环菜单
	 * @param menu
	 * @param isSuper
	 * @return
	 */
	private List<Resource> getChildLink(Resource menu,String isSuper)
	{
		/*
		 *  按照角色查询授权的菜单的信息
		 *  超级管理员的角色 默认所有的菜单权限 
		 */
		List<Resource> childList = null;
		if(CommonCons.Y_N_ENUM.Y.toString().equals(isSuper)){
			childList = resService.getResourceByParent(menu.getId());
		}else{
			childList = resService.getPermissionResourceByParent(menu.getId(),SessionUtils.getCurrentUserId());
		}
		menu.setChildRes(childList);
		if(null!=childList){
			for(Resource res : childList){
				getChildLink(res,isSuper);
			}
		}
		return childList;
	}
	
	/**
	 * 获取有链接的菜单的链接
	 * @param resList
	 * @return
	 */
	private String clickMenu(String resId,List<Resource> resList)
	{
		if(null!=resList && resList.size()>0)
		{
			List<Resource> childResList = resList.get(0).getChildRes();
			if(null!=childResList && childResList.size()>0){
				resId = clickMenu(resId,childResList);
			}else{
				resId = resList.get(0).getId();
			}
		}
		
		return resId;
	}
	
	/**
	 * 面包屑导航字符串
	 * @param res
	 * @param path
	 * @return
	 */
	private String navstationPath(Resource res,String path){
		path = ">&nbsp;&nbsp;"+res.getResName() + "&nbsp;&nbsp;" + path;
		if(null!= res.getParent() && StringUtils.isNotBlank(res.getParent())){
			Resource parentRes = resService.getResourceById(res.getParent());
			path = navstationPath(parentRes,path);
		}
		return path;
	}
	
	
	/**
	 * 在线用户信息
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="security/onLineUser")
	public String listUser(HttpServletRequest request,Model model,VisitLog log,Integer pageNum)
	{
		List<? extends Object> onlineUser = securityService.loadAllOnlineUser();
		model.addAttribute("onlineUser",onlineUser);
		return "security/list/onlineUserList";
	}
	
	
	/**
	 * 踢出用户
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("security/kickout")
	public ResultObject kickout(Model model, HttpServletRequest request) 
	{
		ResultObject result = new ResultObject();
		try {
			String sessionId = request.getParameter("sessionId");
			String user = request.getParameter("userName");
			
			if(!StringUtils.isEmpty(sessionId)){
				securityService.kickoutUser(sessionId);
//				 MessageUtils.sendMessage2User(sessionId, "/topic/kickout", "踢踢踢踢");
				MessageUtils.sendMessage2User(user, "/message", new TextMessage("提示消息","您被管理员提出系统，请重新登录！"));
				// template.convertAndSendToUser(user, "/message",user );  
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("用户已经已被成功踢出！");
			}else{
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("参数SessionID 为空 ");
			}
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.fmtError(this.getClass(), "踢出用户异常 ：%", e.getMessage());
		}
		return result;
	}
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
   public String greeting2(String userName) throws Exception {
	    System.out.println("receiving " + userName);
		System.out.println("connecting successfully.");
		template.convertAndSendToUser(userName, "/topic/greetings", userName);  
		return "Hello, " +userName;
    }
	
    /** 
     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中， 
     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。 
     * @return 
     */  
/*    @MessageMapping("/message")  
    @SendToUser("/message")  
    public String handleSubscribe() {  
        System.out.println("this is the @SubscribeMapping('/marco')");  
        return  "I am a msg from SubscribeMapping('/macro')." ;  
    }  */
  
    /** 
     * 测试对指定用户发送消息方法 
     * @return 
     */  
   /* @RequestMapping(path = "/send", method = RequestMethod.GET)  
    public String send() {  
        template.convertAndSendToUser("1", "/message", );  
        return new "I am a msg from SubscribeMapping('/macro')." ;  
    } */
	
}
