package com.innovate.automate.code.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovate.automate.code.model.CodeGenSetting;
import com.innovate.automate.code.util.CodeGenUtils;
import com.innovate.basic.base.BaseController;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SystemPropertiesUtil;

/**
 * 代码自动生成设置Controller
 * @author IvanHsu
 * @2018年4月13日 上午11:11:50
 */
@Controller("automate.controller.CodeGenSetController")
public class CodeGenSetController extends BaseController{

	/**
	 * 生成代码设置页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("code/genSet")
	public String genSetPage(HttpServletRequest request,ModelMap model)
	{
		try {
			Properties setPro = SystemPropertiesUtil.getPropertiesFile(SystemPropertiesUtil.class.getResource("/").toURI().getPath()
					+ "config" + File.separator + "gen_config.properties");
			CodeGenSetting codeSet = new CodeGenSetting();
			if(null!=setPro){
				codeSet.setDbDriver(setPro.getProperty(CodeGenUtils.GEN_DB_DRIVER));
				codeSet.setDbUrl(setPro.getProperty(CodeGenUtils.GEN_DB_URL));
				codeSet.setDbUser(setPro.getProperty(CodeGenUtils.GEN_DB_USER));
				codeSet.setDbPass(setPro.getProperty(CodeGenUtils.GEN_DB_PASSWORD));
				codeSet.setTargetProject(setPro.getProperty(CodeGenUtils.GEN_TARGET_PATH));
				codeSet.setWorkPath(setPro.getProperty(CodeGenUtils.GEN_WORKSPACE_PATH));
			}
			model.addAttribute("codeSet", codeSet);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			LoggerUtils.error(getClass(), "请检查属性配置文件 /config/gen_config.properties 是否存在！");
		}
		return "automate/code/genSet";
	}
	
	
	/**
	 * 加载当前的配置的数据源信息
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="code/loadDataSource")
	public CodeGenSetting loadDataSource(HttpServletRequest request,Model model,String id)
	{
		CodeGenSetting codeSet = new CodeGenSetting();
		try{
			Properties dbPro = SystemPropertiesUtil.getPropertiesFile(SystemPropertiesUtil.class.getResource("/").toURI().getPath()
						+ "db" + File.separator + "db.properties");
			if(null!=dbPro){
				codeSet.setDbDriver(dbPro.getProperty("jdbc.driver"));
				codeSet.setDbPass(dbPro.getProperty("jdbc.password"));
				codeSet.setDbUrl(dbPro.getProperty("jdbc.url"));
				codeSet.setDbUser(dbPro.getProperty("jdbc.username"));
			}
		}catch (Exception e) {
			LoggerUtils.error(getClass(), e.getMessage());
		}
		return codeSet;
	}
	
	/**
	 * 保存更新设置信息
	 * @param request
	 * @param model
	 * @param codeGenSetting
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="code/saveCodeSet")
	public ResultObject modiCodeSet(HttpServletRequest request,Model model, CodeGenSetting codeGenSetting)
	{
		ResultObject result = new ResultObject();
		try{
			String filePath = SystemPropertiesUtil.class.getResource("/").toURI().getPath() + "config" + File.separator + "gen_config.properties";
			Properties setPro = SystemPropertiesUtil.getPropertiesFile(filePath);
			if(null!=setPro){
				setPro.setProperty(CodeGenUtils.GEN_DB_DRIVER, codeGenSetting.getDbDriver());
				setPro.setProperty(CodeGenUtils.GEN_DB_URL, codeGenSetting.getDbUrl());
				setPro.setProperty(CodeGenUtils.GEN_DB_USER, codeGenSetting.getDbUser());
				setPro.setProperty(CodeGenUtils.GEN_DB_PASSWORD, codeGenSetting.getDbPass());
				setPro.setProperty(CodeGenUtils.GEN_TARGET_PATH, codeGenSetting.getTargetProject());
				setPro.setProperty(CodeGenUtils.GEN_WORKSPACE_PATH, codeGenSetting.getWorkPath());
				
				// 降配置信息协会到属性文件中
//				String workSpace = codeGenSe?tting.getWorkPath();
				OutputStream out = null;
				/*if(StringUtils.isNotBlank(workSpace)){
					String fileLoginPath = filePath + File.separator+ "resources" + File.separator+ "config" + File.separator + "gen_config.properties";
					out = new FileOutputStream(fileLoginPath);
					setPro.store(out, "更新保存配到开发工程文件");
				}*/
				out = new FileOutputStream(filePath);
				setPro.store(out, "更新保存配置的信息");
				out.flush();
				out.close();
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
			}
		}catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("保存失败");
			LoggerUtils.error(getClass(), e.getMessage());
		}
		return result;
	}
	
}
