package com.innovate.automate.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovate.automate.code.model.CodeGenSetting;
import com.innovate.automate.code.model.TableInfo;
import com.innovate.automate.code.util.CodeGenUtils;
import com.innovate.automate.util.TableUtils;
import com.innovate.basic.base.BaseController;
import com.innovate.util.ResultObject;

/**
 * 代码生成
 * @author IvanHsu
 * @2018年4月16日 上午10:43:10
 */
@Controller("automate.code.controller.CodeGenController")
public class CodeGenController extends BaseController{
	
	/**
	 * 获取table的列表信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gen/tableList")
	public String dbManage(HttpServletRequest request,HttpServletResponse response,ModelMap model)
	{
		CodeGenSetting genSet = CodeGenUtils.getCodeSetting(); 
		if(null!=genSet){
			List<TableInfo> tableList = TableUtils.getTables(genSet.getDbDriver(),genSet.getDbUrl(),genSet.getDbUser(),genSet.getDbPass());
			model.addAttribute("tableList", tableList);
		}
		return "automate/db/tableList";
	}
	
	/**
	 * 选择表代码生成 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("gen/codeCreated")
	public ResultObject codeGen( HttpServletRequest request, HttpServletResponse response)
	{
		ResultObject result = new ResultObject();
		String[] genTypesArr = request.getParameterValues("genType");
		String[] tableNames = request.getParameterValues("tableName");
		String[] domainNames = request.getParameterValues("domainName");
		String packagePath = request.getParameter("packagePath");
		String workPath = request.getParameter("workPath");
		CodeGenSetting genSet = CodeGenUtils.getCodeSetting(); 
		if(null == genSet){
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("请先完成设置信息");
		}else{
			workPath = StringUtils.isEmpty(workPath) ?  genSet.getTargetProject() : workPath;
			boolean bol = CodeGenUtils.genMySQLCode(genSet,packagePath,tableNames,domainNames,genTypesArr,workPath);
			if(bol){
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("代码生成成功");
			}else{
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("代码生成失败，请查看日志");
			}
		}
		return result;
	}
	
}
