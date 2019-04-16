package com.innovate.sys.security.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovate.basic.annotation.SystemVisitLog;
import com.innovate.basic.exception.AccountExisitException;
import com.innovate.core.util.MessageUtils;
import com.innovate.core.util.TextMessage;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.log.service.IVisitLogService;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.model.Resource;
import com.innovate.sys.resource.service.IResourceService;
import com.innovate.user.role.service.IResRoleService;
import com.innovate.user.role.service.IRoleResOptService;
import com.innovate.user.role.service.IRoleService;
import com.innovate.user.user.model.User;
import com.innovate.user.user.service.IUserService;
import com.innovate.util.CommonCons;
import com.innovate.util.CommonUtils;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SessionUtils;

/**
 * @desc:登录
 * @time: 2017年6月12日 下午4:42:04
 * @author IvanHsu
 */
@Controller("security.LoginController")
public class LoginController {

	@Autowired
	private DicUtil dicUtil;
	@Autowired
	private IResourceService resService;
	@Autowired
	private IResRoleService reseRoleService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleResOptService roleResOptServ;
	@Autowired
	private IVisitLogService visitLogServ;
	
	/**
	 * 登录调整
	 * 
	 * @param model
	 * @param request
	 * @return String 2017年6月19日
	 */
	@RequestMapping("login")
	public String toLogin(Model model, HttpServletRequest request) 
	{
		String page = "";
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated())
			page = "redirect:main";
		else {
			page = "security/login";
		}
		return page;
	}

	/**
	 * 登录操作
	 * @param model
	 * @param request
	 * @param user
	 * @return String 2017年6月19日
	 */
	@ResponseBody
	@RequestMapping("submitLogin")
	@SystemVisitLog(description="用户登录",visitType="登录")
	public ResultObject submitLogin(Model model, HttpServletRequest request, User user,Boolean rememberMe) 
	{
		ResultObject result = new ResultObject();
		Subject currentUser = SecurityUtils.getSubject();
		rememberMe = null == rememberMe ?  false : rememberMe;
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken usernamePasswordToken = 
					new UsernamePasswordToken(user.getLoginName(),user.getPassword(),rememberMe);
			try {
				currentUser.login(usernamePasswordToken);
				User userLogin = (User) currentUser.getPrincipal();
				//更新登录信息
				userLogin.setLastLoginTime(new Date());
				userLogin.setLastLoginIp(CommonUtils.getLoginIpAddr(request));
				userService.saveOrUpdateUser(userLogin);
				this.initLoginUserInfo();
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				MessageUtils.sendMessage2All("/topic/greetings", new TextMessage("登录广播提示",userLogin.getUserName()+"登录了系统"));
				
			}catch (AccountExisitException e) {
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage(e.getMessage());
				LoggerUtils.fmtError(getClass(), e.getMessage(), e);
			}catch (UnknownAccountException e) {
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("账号不存在");
				LoggerUtils.fmtError(getClass(), "登录验证异常", e);
			}catch (DisabledAccountException e) {
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("帐号已经禁止登录!");
				LoggerUtils.fmtError(getClass(), "登录验证异常", e);
			}catch(AuthenticationException e) {
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("账号密码验证错误，请重新输入");
				LoggerUtils.fmtError(getClass(), "登录验证异常", e);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: main @Description: 登录成功之后的跳转到首页
	 * request @return @throws
	 */
	@RequestMapping("main")
	public String main(Model model, HttpServletRequest request) 
	{
		Subject subject = SecurityUtils.getSubject();
		User currentUser = (User) subject.getPrincipal();
		if(null!=currentUser){
			//统计登录的信息
			StringBuffer series = new StringBuffer();
			StringBuffer categories = new StringBuffer();
			StringBuffer loginSeries = new StringBuffer();
			StringBuffer logoutSeries = new StringBuffer();
			List<Map<String, Object>> resultMap = visitLogServ.countVisitLog();
			if(!resultMap.isEmpty()){
				categories.append("[");
				loginSeries.append("[");
				logoutSeries.append("[");
				series.append("[");
				int idx = 0;
				for(Map<String, Object> map : resultMap){
					if(idx > 0){
						categories.append(",");
						loginSeries.append(",");
						logoutSeries.append(",");
					}
					categories.append("'").append(map.get("visitdate")).append("'");
					loginSeries.append(map.get("loginnum"));
					logoutSeries.append(map.get("logoutmum"));
					idx++;
				}
				
				categories.append("]");
				loginSeries.append("]");
				logoutSeries.append("]");
				
				series.append("{ name: '登录',data:").append(loginSeries).append("},");
				series.append("{ name: '退出',data:").append(logoutSeries).append("}");
				series.append("]");
				
				model.addAttribute("categories", categories.toString());
				model.addAttribute("series",series.toString());
			}
		}
		SessionUtils.removeSessionAttribute("_currentMenu");
		SessionUtils.removeSessionAttribute("_navstation_path");
		return "default_main";
	}
	
	/**
	 * 初始化加载登录用户的信息
	 * @param loginUser
	 */
	private void initLoginUserInfo()
	{
		Subject currentUser = SecurityUtils.getSubject();
		User userLogin = (User) currentUser.getPrincipal();
		if(null!=userLogin)
		{
			 // 按照角色查询授权的菜单的信息
	         String isSuper = userLogin.getIsSuperUser();
	         if(CommonCons.Y_N_ENUM.Y.toString().equals(isSuper)){
	        	 /*
	        	  * 获取超级管理员的菜单的信息
	        	  * 不获取对应的菜单的操作信息， 默认超级管理员配有所有的操作权限
	        	  */
	        	 SessionUtils.setSessionAttribute("rootLinkList",resService.getAllRootResource());
	        	 List<Resource> linkList = resService.getAdminResource();
	        	 Dic menuType = dicUtil.getDicInfo("MENU_TYPE", "MENU_TYPE_FUN");
		         Map<String,List<Opt>> menuOptMap = new HashMap<String,List<Opt>>();
		         if(null != linkList){
//		        	 List<String> roleIdList = roleService.getRoleByUserId(userLogin.getId());
		        	 for(Resource resource : linkList){
		        		 if(menuType.getId().equals(resource.getMenuType())){//只有功能节点的菜单才有设置的操作权限
		        			 menuOptMap.put(resource.getId(), roleResOptServ.getOptsByRes(resource.getId()));
		        		 }
		        	 }
		         }
		         SessionUtils.setSessionAttribute("_menuOptMap", menuOptMap); 
	         }else{
	        	 List<String> roleList = roleService.getRoleByUserId(userLogin.getId());
	        	 SessionUtils.setSessionAttribute("rootLinkList", reseRoleService.getRootResByRoleList(roleList));
	        	 /**
	        	  * 有权限的菜单上的
	        	  * 操作授权
	        	  */
	        	 List<Resource> optLinkedList = reseRoleService.getResByRoleList(roleList);
		         Dic menuType = dicUtil.getDicInfo("MENU_TYPE", "MENU_TYPE_FUN");
		         Map<String,List<Opt>> menuOptMap = new HashMap<String,List<Opt>>();
		         if(null != optLinkedList){
		        	 List<String> roleIdList = roleService.getRoleByUserId(userLogin.getId());
		        	 for(Resource resource : optLinkedList){
		        		 if(menuType.getId().equals(resource.getMenuType())){//只有功能节点的菜单才有设置的操作权限
		        			 menuOptMap.put(resource.getId(), roleResOptServ.getOptsByResRole(resource.getId(),roleIdList));
		        		 }
		        	 }
		         }
		         SessionUtils.setSessionAttribute("_menuOptMap", menuOptMap); 
	         }
	         SessionUtils.setSessionAttribute(CommonCons.SESSION_USER_KEY,userLogin);
	         SessionUtils.setSessionAttribute(CommonCons.SESSION_USER_LOGIN_NAME_KEY,userLogin.getLoginName());
		}
	}
	
	/**
	 * 退出操作
	 * @param model
	 * @param request
	 * @return String 2017年6月19日
	 */
	@RequestMapping("logout")
	public String logout(Model model, HttpServletRequest request) 
	{
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:login.do";
	}

}
