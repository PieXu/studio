package com.innovate.user.role.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.model.ResTreeOb;
import com.innovate.sys.resource.model.Resource;
import com.innovate.sys.resource.service.IResOptService;
import com.innovate.sys.resource.service.IResourceService;
import com.innovate.user.role.model.Role;
import com.innovate.user.role.model.RoleResOpt;
import com.innovate.user.role.service.IResRoleService;
import com.innovate.user.role.service.IRoleResOptService;
import com.innovate.user.user.controller.UserController;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;

/**
 * 角色授权 操作
 * @author IvanHsu
 * @2018年3月30日 下午1:46:13
 */
@Controller("sys.controller.RoleGrantController")
public class RoleGrantController extends BaseController{

	@Autowired
	private DicUtil dicUtil;
	@Autowired
	private IResourceService resService;
	@Autowired
	private IResRoleService resRoleService;
	@Autowired
	private IResOptService resOptService;
	@Autowired
	private IRoleResOptService roleResOptService;
	
	/**
	 * 授权list
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"grant/grantList"})
	public String grantList(HttpServletRequest request,Model model){
		//菜单列表
		List<Resource> treeList = new ArrayList<Resource>(); 
		List<Resource> resList = resService.getAllRootResource();
		for(Resource res : resList){
			treeList.add(res);
			setChildList(res.getId(),treeList);
		}
		List<ResTreeOb> resTree = new ArrayList<ResTreeOb>();
		resTree.add(ResTreeOb.getRootTreeObj("菜单管理"));
		for(Resource res : treeList){
			ResTreeOb treeObj = new ResTreeOb();
			treeObj.setName(res.getResName());
			treeObj.setId(res.getId());
			treeObj.setMenuType(res.getMenuType());
			if(null == res.getParent() || StringUtils.isEmpty(res.getParent())){
				treeObj.setpId(ResTreeOb.ROOT_ID);
				treeObj.setOpen(true);
			}else{
				treeObj.setpId(res.getParent());
			}
			resTree.add(treeObj);
		}
		String resJson = JSONArray.toJSONString(resTree);
		model.addAttribute("resJson", resJson);
		//只有功能菜单才设置操作
		model.addAttribute("menuFunType", dicUtil.getDicInfo("MENU_TYPE", "MENU_TYPE_FUN"));
		return "grant/list/grantList";
	}

	
	private void setChildList(String parent, List<Resource> treeList) {
		List<Resource> childList = resService.getResourceByParent(parent);
		if (null != childList) {
			for (Resource res : childList) {
				treeList.add(res);
				setChildList(res.getId(), treeList);
			}
		}
	}
	
	/**
	 * 角色操作列表
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value={"grant/roleOptList"})
	public String roleOptList(HttpServletRequest request,Model model,String menuId)
	{
		if(StringUtils.isNotBlank(menuId)){
			// 菜单上配置的操作
			List<Opt> optList = resOptService.getOptByResId(menuId);
			model.addAttribute("optList",optList);
			// 菜单上配置的角色信息
			List<Role> roleList = resRoleService.getRoleByResId(menuId);
			model.addAttribute("roleList",roleList);
			
			List<RoleResOpt> roleResOptList = roleResOptService.getRoleOptByRes(menuId);
			if(!roleResOptList.isEmpty()){
				List<String> checkList = new ArrayList<String>();
				for(RoleResOpt obj : roleResOptList){
					checkList.add(obj.getOptId()+"_"+obj.getRoleId());
				}
				model.addAttribute("checkList",checkList);
			}
		}
		model.addAttribute("menuId",menuId);
		return "grant/empty/roleOptList";
	}
	
	/**
	 * 保存授权信息
	 * @param request
	 * @param model
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"grant/saveRoleOpt"})
	public ResultObject saveRoleOpt(HttpServletRequest request,Model model)
	{
		ResultObject result = new ResultObject();
		try {
			//设置的选中的信息
			String[] roleOpts = request.getParameterValues("role_opt_check");
			String menuId = request.getParameter("menuId");
			roleResOptService.saveRoleResOpt(menuId,roleOpts);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
			result.setMessage("角色授权操作成功");
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(UserController.class,e.getMessage());
		}
		return result;
	}
	
}
