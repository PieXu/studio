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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.resource.model.ResTreeOb;
import com.innovate.sys.resource.model.Resource;
import com.innovate.sys.resource.service.IResourceService;
import com.innovate.user.role.model.Role;
import com.innovate.user.role.service.IResRoleService;
import com.innovate.user.role.service.IRoleService;
import com.innovate.util.CommonCons;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;

/**
 * 角色管理Controller
 * @author
 */
@Controller("user.RoleController")
public class RoleController extends BaseController{

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IResourceService resService;
	@Autowired
	private IResRoleService resRoleService;
	
	/**
	 * 角色列表查询
	 * @param request
	 * @param model
	 * @param role
	 * @return
	 */
	@RequestMapping(value="role/roleList")
	public String listRole(HttpServletRequest request,Model model,Role role,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<Role> page = roleService.pageRole(role);;
		model.addAttribute("page",page);
		model.addAttribute("role", role);
		return "role/list/roleList";
	}
	
	/**
	 * 修改 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"role/roleAdd","role/roleEdit"})
	public String roleEdit(HttpServletRequest request,Model model,Role role)
	{
		String id = role.getId();
		if(StringUtils.isNotBlank(id)){
			role = roleService.getRoleById(id);
		}
		model.addAttribute("role", role);
		return "role/nsm/roleEdit";
	}
	
	
	/**
	 * 信息保存
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"role/saveRole"})
	public ResultObject saveRole(HttpServletRequest request,Model model,Role role)
	{
		ResultObject result = new ResultObject();
		try {
			roleService.saveOrUpdateRole(role);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(RoleController.class,e.getMessage());
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
	@RequestMapping(value="role/deleteRole")
	public ResultObject deleteRole(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				int count = roleService.checkUserRole(id);
				if(count > 0 ){
					result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
					result.setMessage("当前存在用户使用该角色，不能删除");
				}else{
					roleService.deleteRole(id);
					result.setResult(ResultObject.OPERATE_RESULT.success.toString());
					result.setMessage("该角色已删除");
				}
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
	 * 菜单授权
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"role/grantRes"})
	public String grantRes(HttpServletRequest request,Model model,String id)
	{
		/*if(StringUtils.isNotBlank(id)){
			role = roleService.getRoleById(id);
		}*/
		
		//菜单列表
		List<Resource> treeList = new ArrayList<Resource>(); 
		List<Resource> resList = resService.getAllRootResource();
		for(Resource res : resList){
			treeList.add(res);
			setChildList(res.getId(),treeList);
		}
		List<ResTreeOb> resTree = new ArrayList<ResTreeOb>();
		resTree.add(ResTreeOb.getRootTreeObj("菜单管理"));
		boolean bol = false;
		for(Resource res : treeList){
			ResTreeOb treeObj = new ResTreeOb();
			treeObj.setName(res.getResName());
			treeObj.setId(res.getId());
			bol = resRoleService.checkRoleHasRes(id,res.getId());
			treeObj.setChecked(bol);
			treeObj.setOpen(bol);
			if(null == res.getParent() || StringUtils.isEmpty(res.getParent())){
				treeObj.setpId(ResTreeOb.ROOT_ID);
			}else{
				treeObj.setpId(res.getParent());
			}
			resTree.add(treeObj);
		}
		
		String resJson = JSONArray.toJSONString(resTree);
		model.addAttribute("resJson", resJson);
		model.addAttribute("roleId", id);
		
		return "role/nsm/grantRes";
	}
	
	private void setChildList(String parent,List<Resource> treeList ) {
		List<Resource> childList = resService.getResourceByParent(parent);
		if(null!=childList){
			for(Resource res : childList){
				treeList.add(res);
				setChildList(res.getId(),treeList);
			}
		}
	}
	
	/**
	 * 保存角色菜单授权信息
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="role/saveRoleRes")
	public ResultObject saveRoleRes(HttpServletRequest request,Model model)
	{
		ResultObject result = new ResultObject();
		String roleId = request.getParameter("roleId");
		String ids = request.getParameter("ids");
		if(StringUtils.isNotBlank(roleId)){
			try{
				resRoleService.saveRoleRes(roleId,ids);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("角色授权成功");
			}catch (Exception e) {
				LoggerUtils.error(this.getClass(), e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 roleId 为空");
		}
		return result;
	} 
	
	
}
