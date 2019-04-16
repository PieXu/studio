package com.innovate.sys.resource.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovate.basic.base.BaseController;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.service.IOptService;
import com.innovate.user.role.controller.RoleController;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;

/**
 * 菜单操作管理
 * @author IvanHsu
 * @2018年3月26日 上午10:30:16
 */
@Controller("sys.controller.OptController")
public class OptController extends BaseController{
	
	@Autowired
	private IOptService resOptService;
	
	/**
	 * 查询所有的操作信息 页面展示
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("opt/optList")
	public String listAllOpt(HttpServletRequest request,Model model)
	{
		List<Opt> optList = resOptService.getAllOpt();
		model.addAttribute("optList", optList);
		return "opt/optList";
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
	@RequestMapping(value={"opt/optAdd","opt/optEdit"})
	public String optEdit(HttpServletRequest request,Model model,Opt opt)
	{
		String id = opt.getId();
		if(StringUtils.isNotBlank(id)){
			opt = resOptService.getOptById(id);
		}
		model.addAttribute("opt", opt);
		return "opt/nsm/optEdit";
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
	@RequestMapping(value={"opt/saveOpt"})
	public ResultObject saveOpt(HttpServletRequest request,Model model,Opt opt)
	{
		ResultObject result = new ResultObject();
		try {
			resOptService.saveOrUpdateOpt(opt);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(RoleController.class,e.getMessage());
		}
		return result;
	}
	
	
	/**
	 *检查是否可以删除
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"opt/checkDelete"})
	public ResultObject checkDelete(HttpServletRequest request,Model model)
	{
		ResultObject result = new ResultObject();
		try {
			String[] optIds = request.getParameterValues("optId");
			int count = resOptService.checkDelete(optIds);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
			if(count>0){
				result.setMessage("当前选中的操作中有被菜单引用，是否删除没有被引用的操作吗？");
			}
			else{
				result.setMessage("确定要删除操作信息吗？");
			}
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(RoleController.class,e.getMessage());
		}
		return result;
	}
	
	/**
	 *检查是否可以删除
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"opt/deleteOpts"})
	public ResultObject deleteOpts(HttpServletRequest request,Model model)
	{
		ResultObject result = new ResultObject();
		try {
			String[] optIds = request.getParameterValues("optId");
			if(ArrayUtils.isNotEmpty(optIds)){
				resOptService.deleteOpts(optIds);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("操作已成功删除！");
			}
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(RoleController.class,e.getMessage());
		}
		return result;
	}
	
	
}
