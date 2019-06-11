package com.innovate.user.user.controller;

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
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.user.user.model.UserGroup;
import com.innovate.user.user.service.IUserGroupService;
import com.innovate.user.user.service.IUserService;
import com.innovate.util.CommonCons;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;

/**
 * 用户组管理
 * @time: 2017年6月20日 下午1:10:06
 * @author IvanHsu 
 */
@Controller("user.UserGroupController")
public class UserGroupController extends BaseController{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserGroupService userGroupService;
	@Autowired
	private DicUtil dicUtil;
	
	/**
	 * 用户組信息列表查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="group/groupList")
	public String groupList(HttpServletRequest request,Model model,UserGroup group,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		group.setDelFlag(CommonCons.STATUS_NORMAL);
		Page<UserGroup> page = userGroupService.pageUserGroup(group);
		model.addAttribute("group",group);//用於值的回顯
		model.addAttribute("page",page);
		return "user/groupList";
	}
	
	/**
	 * 用户組修改 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"group/groupAdd","group/groupEdit"})
	public String editUserGroup(HttpServletRequest request,Model model,UserGroup userGroup)
	{
		String id = userGroup.getId();
		if(StringUtils.isNotBlank(id)){
			userGroup = userGroupService.getUserGroupById(id);
		}
		model.addAttribute("userGroup", userGroup);
		return "user/nsm/groupEdit";
	}
	
	/**
	 * 删除对象
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="group/deleteUserGroup")
	public ResultObject deleteUser(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				userGroupService.logicDeleteById(id);
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
	 * 用户組信息保存
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"group/saveUserGroup"})
	public ResultObject saveUser(HttpServletRequest request,Model model,UserGroup userGroup)
	{
		ResultObject result = new ResultObject();
		try {
			userGroupService.saveOrUpdateUserGroup(userGroup);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(UserGroupController.class,e.getMessage());
		}
		return result;
	}
	
}
