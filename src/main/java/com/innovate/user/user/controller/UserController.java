package com.innovate.user.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.user.role.model.Role;
import com.innovate.user.role.service.IRoleService;
import com.innovate.user.user.model.User;
import com.innovate.user.user.service.IUserService;
import com.innovate.util.CommonCons;
import com.innovate.util.LoggerUtils;
import com.innovate.util.MD5Utils;
import com.innovate.util.ResultObject;
import com.innovate.util.SystemPropertiesUtil;

/**
 * @time: 2017年6月20日 下午1:10:06
 * @author IvanHsu 
 */
@Controller("user.UserController")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private DicUtil dicUtil;
	
	/**
	 * 用户信息列表查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="user/userList")
	public String listUser(HttpServletRequest request,Model model,User user,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
//		user.setIsSuperUser(isSuperUser);
		user.setDelFlag(CommonCons.STATUS_NORMAL);
		Page<User> page = userService.pageUser(user);
		model.addAttribute("page",page);
		List<Dic> genderList = dicUtil.getDicList("GENDER");
		List<Dic> userTypeList = dicUtil.getDicList("USER_TYPE");
		List<Dic> userStateList = dicUtil.getDicList("USER_STATE");
		model.addAttribute("genderList",genderList);
		model.addAttribute("userTypeList",userTypeList);
		model.addAttribute("userStateList",userStateList);
		model.addAttribute("userStateEnable",dicUtil.getDicInfo("USER_STATE","USER_STATE_ENABLE"));
		return "user/userList";
	}
	
	/**
	 * 用户修改 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"user/userAdd","user/userEdit"})
	public String editUser(HttpServletRequest request,Model model,User user)
	{
		String id = user.getId();
		if(StringUtils.isNotBlank(id)){
			user = userService.getUserById(id);
		}
		model.addAttribute("user", user);
		model.addAttribute("genderList",dicUtil.getDicList("GENDER"));
		model.addAttribute("userTypeList",dicUtil.getDicList("USER_TYPE"));
		model.addAttribute("userStateList",dicUtil.getDicList("USER_STATE"));
		return "user/nsm/userEdit";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"user/changPass"})
	public ResultObject changPass(HttpServletRequest request,Model model,User user)
	{
		ResultObject result = new ResultObject();
		try {
			String id = user.getId();
			if(!StringUtils.isEmpty(id)){
				// 设置加密密码
				String salt = MD5Utils.getSalt(4);
				user.setSalt(salt);
				String mpass = MD5Utils.encoderPassword(salt, user.getPassword());
				user.setPassword(mpass);
				userService.saveOrUpdateUser(user);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("密码修改成功！");
			}else{
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("参数ID 为空 ");
			}
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(UserController.class,e.getMessage());
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
	@RequestMapping(value="user/deleteUser")
	public ResultObject deleteUser(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				userService.logicDeleteById(id);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("该用户已删除");
			}catch (Exception e) {
				LoggerUtils.error(this.getClass(), e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
	/**
	 * 重置密码吗
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="user/resetPassword")
	public ResultObject resetPassword(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				User user = new User();
				user.setId(id);
				// 设置加密密码
				String salt = MD5Utils.getSalt(4);
				user.setSalt(salt);
				String mpass = MD5Utils.encoderPassword(salt, SystemPropertiesUtil.getSystemConfigProperties().getProperty(CommonCons.DEFAULE_PASS_WORD).toString() );
				user.setPassword(mpass);
				userService.saveOrUpdateUser(user);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("用户重置密码已成功");
			}catch (Exception e) {
				LoggerUtils.error(this.getClass(), e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("用户重置密码失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
	/**
	 * 启用禁用
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="user/changeStatus")
	public ResultObject changeStatus(HttpServletRequest request,Model model,String id,String status)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				User user = new User();
				user.setId(id);
				
				if(StringUtils.isNotBlank(status) && "0".equals(status)){
					Dic dic = dicUtil.getDicInfo("USER_STATE","USER_STATE_ENABLE");
					user.setStatus(dic.getId());
					result.setMessage("用户已成功启用");
				}else if(StringUtils.isNotBlank(status) && "-1".equals(status)){
					Dic dic = dicUtil.getDicInfo("USER_STATE","USER_STATE_DISABLE");
					user.setStatus(dic.getId());
					result.setMessage("用户已成功禁用用");
				}
				userService.saveOrUpdateUser(user);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
			}catch (Exception e) {
				LoggerUtils.error(this.getClass(), e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
	/**
	 * 用户角色列表
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"user/userRoleList"})
	public String userRoleList(HttpServletRequest request,Model model,String id)
	{
		if(StringUtils.isNotBlank(id)){
			List<Role> roleList = roleService.getRoleList();
			List<String> userRoleList = roleService.getRoleByUserId(id);
			model.addAttribute("roleList",roleList);
			model.addAttribute("userRoleList",userRoleList);
			model.addAttribute("userId",id);
		}
		return "user/nsm/userRole";
	}
	
	/**
	 * 保存用户角色信息
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="user/saveUserRole")
	public ResultObject saveUserRole(HttpServletRequest request,Model model)
	{
		ResultObject result = new ResultObject();
		String userId = request.getParameter("userId");
		String[] roleIds  = request.getParameterValues("roleId");
		if(!StringUtils.isEmpty(userId)){
			try{
				userService.saveUserRole(userId,roleIds);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("用户角色授权成功");
			}catch (Exception e) {
				LoggerUtils.error(UserController.class, e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
	
	/**
	 * 用户信息保存
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"user/saveUser"})
	public ResultObject saveUser(HttpServletRequest request,Model model,User user)
	{
		ResultObject result = new ResultObject();
		try {
			String id = user.getId();
			if(StringUtils.isEmpty(id)){
				user.setCreateTime(new Date());
				// 设置加密密码
				String salt = MD5Utils.getSalt(4);
				user.setSalt(salt);
				String mpass = MD5Utils.encoderPassword(salt, user.getPassword());
				user.setPassword(mpass);
				user.setRegTime(new Date());
				user.setIsSuperUser(CommonCons.Y_N_ENUM.N.toString());
				user.setDelFlag(CommonCons.STATUS_NORMAL);
			}
			userService.saveOrUpdateUser(user);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(UserController.class,e.getMessage());
		}
		return result;
	}
}
