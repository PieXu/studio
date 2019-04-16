package com.innovate.sys.resource.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.model.Resource;
import com.innovate.sys.resource.service.IOptService;
import com.innovate.sys.resource.service.IResOptService;
import com.innovate.sys.resource.service.IResourceService;
import com.innovate.user.user.controller.UserController;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;

/**
 * 
 * 资源管理
 * @author 
 *
 */
@Controller("sys.ResourceController")
public class ResourceController extends BaseController{
	@Autowired
	private DicUtil dicUtil;
	@Autowired
	private IResourceService resService;
	@Autowired
	private IResOptService resOptService;
	@Autowired
	private IOptService optService;
	
	/**
	 * 菜单管理跳转，默认展开顶级的菜单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/res/resourceList")
	public String resourceManage(HttpServletRequest request,Model model)
	{
		List<Resource> treeList = new ArrayList<Resource>(); 
		List<Resource> resList = resService.getAllRootResource();
		for(Resource res : resList){
			treeList.add(res);
			setChildList(res.getId(),treeList);
		}
		model.addAttribute("treeList", treeList);
		model.addAttribute("menuType", dicUtil.getDicInfo("MENU_TYPE", "MENU_TYPE_FUN"));
		
		return "res/resourceManage";
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
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"res/addRes","res/editRes"})
	public String modifyResource(HttpServletRequest request,Model model,String id,String parent)
	{
		Resource res = new Resource();
		if(!StringUtils.isEmpty(id)){
			res =  resService.getResourceById(id);
		}else{
			res.setParent(parent);
		}
		model.addAttribute("typeList", dicUtil.getDicList("MENU_TYPE"));
		model.addAttribute("res", res);
		model.addAttribute("parent", parent);
		return "res/nsm/resEdit";
	}
	
	/**
	 * 保存产品
	 * @param request
	 * @param model
	 * @param breaing
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="res/saveResource")
	public ResultObject saveResource(HttpServletRequest request,Model model, Resource res)
	{
		ResultObject result = new ResultObject();
		try{
			resService.saveOrUpdateResource(res);
			result.setMessage("菜单保存成功");
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		}catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(ResourceController.class,"产品更新保存异常："+e.getMessage(),e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param parent
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/res/checkHasChild")
	public Integer checkHasChild(HttpServletRequest request,Model model,String parent)
	{
		Integer result = null;
		try {
			result = resService.checkHasChild(parent);
		} catch (Exception e) {
			LoggerUtils.error(ResourceController.class,"检查是否存在子菜单异常",e);
		}
		return result;
	}
	
	/**
	 * 删除菜单
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="res/deleteRes")
	public ResultObject deleteRes(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				resService.delResource(id);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("该菜单已删除");
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
	
	/**
	 * 菜单操作授权
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"res/editResOpt"})
	public String editResOpt(HttpServletRequest request,Model model,String resId)
	{
		List<Opt> optList = optService.getAllOpt();
		if(!StringUtils.isEmpty(resId)){
			List<String> resOptList =  resOptService.getOptIdByResId(resId);
			model.addAttribute("resOptList", resOptList);
		}
		model.addAttribute("optList", optList);
		model.addAttribute("resId", resId);
		return "res/nsm/resOpt";
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="res/saveResOpt")
	public ResultObject saveResOpt(HttpServletRequest request,Model model)
	{
		ResultObject result = new ResultObject();
		String resId = request.getParameter("resId");
		String[] optIds  = request.getParameterValues("optId");
		if(!StringUtils.isEmpty(resId)){
			try{
				resOptService.saveResOpt(resId,optIds);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("菜单操作授权成功");
			}catch (Exception e) {
				LoggerUtils.error(UserController.class, e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("菜单操作授权失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 resId 为空");
		}
		return result;
	}
	
	/**
	 * 上调顺序
	 * @param request
	 * @param model
	 * @param id
	 * @param parent
	 * @param seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="res/upSeq")
	public ResultObject upResSeq(HttpServletRequest request,Model model,String id,String parent,Integer seq)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				resService.upResSeq(id,parent,seq);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("菜单顺序调整成功");
			}catch (Exception e) {
				LoggerUtils.error(UserController.class, e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("菜单操作授权失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
	/**
	 * 下调顺序
	 * @param request
	 * @param model
	 * @param id
	 * @param parent
	 * @param seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="res/dowSeq")
	public ResultObject dowResSeq(HttpServletRequest request,Model model,String id,String parent,Integer seq)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				resService.dowResSeq(id,parent,seq);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("菜单顺序调整成功");
			}catch (Exception e) {
				LoggerUtils.error(UserController.class, e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("菜单操作授权失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
	
}
