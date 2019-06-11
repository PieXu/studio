package com.innovate.plat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.automate.code.model.ColumnInfo;
import com.innovate.automate.util.TableUtils;
import com.innovate.basic.base.BaseController;
import com.innovate.plat.model.PageDefine;
import com.innovate.plat.model.PageSet;
import com.innovate.plat.service.IPageDefineService;
import com.innovate.user.user.controller.UserController;
import com.innovate.util.CommonCons;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SessionUtils;

/**
 * 
* <p>Title: PageDefineController</p>
* <p>Description: 页面定义 Controller</p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月15日
 */
@Controller
public class PageDefineController extends BaseController{

	@Autowired
	private IPageDefineService pageDefineservice;
	
	/**      
	 * 列表查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="plat/listPageDefine")
	public String listPageDefine(HttpServletRequest request,Model model,PageDefine pageDefine,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		if(!SessionUtils.checkIsSuperUser()){
			pageDefine.setDelFlag(CommonCons.STATUS_NORMAL);
		}
		Page<PageDefine> page = pageDefineservice.listPageDefine(pageDefine);
		model.addAttribute("page",page);
		model.addAttribute("pageDefine", pageDefine);
		return "plat/pageDefineList";
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"plat/addPageDefine","plat/editPageDefine"})
	public String editPageDefine(HttpServletRequest request,Model model,String id)
	{
		PageDefine pageDefine = new PageDefine();
		if(!StringUtils.isEmpty(id)){
			pageDefine =  pageDefineservice.getById(id);
		}
		model.addAttribute("tableList", TableUtils.getTables());
		model.addAttribute("pageDefine", pageDefine);
		return "plat/nsm/pageDefineEdit";
	}
	
	/**
	 * 
	* <p>Title: savePageDefine</p>
	* <p>Description: 保存信息 </p>
	* @param request
	* @param model
	* @param user
	* @return
	 */
	@ResponseBody
	@RequestMapping(value={"plat/savePageDefine"})
	public ResultObject savePageDefine(HttpServletRequest request,Model model,PageDefine pageDefine)
	{
		ResultObject result = new ResultObject();
		try {
			String id = pageDefine.getId();
			if(StringUtils.isEmpty(id)){
				pageDefine.setOperator(SessionUtils.getCurrentUserId());
				pageDefine.setDelFlag(CommonCons.STATUS_NORMAL);
				//设置查询区域
				pageDefine.setSqlBody(initSelectSQLBody(pageDefine.getTableName()));
			}
			pageDefineservice.saveOrUpdatePageDefine(pageDefine);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(UserController.class,e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	* <p>Title: setPageDefine</p>
	* <p>Description: 页面定义编辑</p>
	* @param request
	* @param model
	* @param id
	* @return
	 */
	@RequestMapping(value={"plat/setPageDefine"})
	public String setPageDefine(HttpServletRequest request,Model model,String id)
	{
		if(!StringUtils.isEmpty(id)){
			PageDefine pageDefine =  pageDefineservice.getById(id);
			if(null!=pageDefine){
				List<PageSet> pageSets = pageDefineservice.getPageSetByPageId(pageDefine.getId());
				if(pageSets.isEmpty()){
					//String[] array = new String[]{"create_time","update_time","del_flag"};
					List<ColumnInfo> columns = TableUtils.getColumns(pageDefine.getTableName(),new String[]{});
					pageSets = new ArrayList<PageSet>();
					for(ColumnInfo column : columns){
						PageSet pageSet = new PageSet();
						pageSet.setAttributeName(column.getAttributeName());
						pageSet.setColumnName(column.getColumnName());
						pageSet.setShowName(column.getColumnCommnet());
						pageSet.setFiledType(column.getTypeName());
						pageSets.add(pageSet);
					}
				}
				model.addAttribute("columns", pageSets);
				model.addAttribute("pageDefine", pageDefine);
			}
		}
		return "plat/nsm/pageDefineSet";
	}
	
	/**
	 * 
	* <p>Title: savePageSet</p>
	* <p>Description: 详细配置保存 </p>
	* @param request
	* @param model
	* @return
	 */
	@ResponseBody
	@RequestMapping(value={"plat/savePageSet"})
	public ResultObject savePageSet(HttpServletRequest request,Model model)
	{
		ResultObject result = new ResultObject();
		try {
			List<PageSet> setList = initSaveSetValue(request);
			pageDefineservice.savePageSet(setList);
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		} catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(UserController.class,e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="plat/deletePageDefine")
	public ResultObject deletePageDefine(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				pageDefineservice.deleteById(id);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("该定义已删除");
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
	 * 
	* <p>Title: initSQL</p>
	* <p>Description: 根据选择的表初始化查询SQL </p>
	* @param request
	* @param model
	* @param tableNames
	* @return
	 */
	private String initSelectSQLBody(String tableNames)
	{
		StringBuffer sql = new StringBuffer();
		if(!StringUtils.isEmpty(tableNames)){
			String[] tables = tableNames.split(",");
			List<ColumnInfo> columns = TableUtils.getColumns(tables,new String[]{});
			sql.append("select ");
			int index = 0 ;
			for(ColumnInfo column : columns){
				if(index > 0){
					sql.append(" , ");
				}
			    sql.append(column.getColumnName())
				   .append(" as ")
				   .append(column.getAttributeName());
				index++;
			}
			sql.append(" from ");
			int tableLen = 0 ;
			for(String table : tables)
			{
				if(tableLen > 0){
					sql.append(" , ");
				}
				sql.append(table);
				tableLen++;
			}
		}
		return sql.toString();
	}
	
	/**
	 * 
	* <p>Title: initSaveSetValue</p>
	* <p>Description: </p>
	* @param request
	* @param pageId
	 */
	private List<PageSet> initSaveSetValue(HttpServletRequest request)
	{
		List<PageSet> setList = new ArrayList<PageSet>();
		String pageId = request.getParameter("pageId");
		String[] ids = request.getParameterValues("id");
		String[] columnNames = request.getParameterValues("columnName");
		String[] attributeNames = request.getParameterValues("attributeName");
		String[] showNames = request.getParameterValues("showName");
		String[] filedTypes = request.getParameterValues("filedType");
		String[] widths = request.getParameterValues("width");
		String[] isQuerys = request.getParameterValues("isQuery");
		String[] dataTypes = request.getParameterValues("dataType");
		String[] dataBodys = request.getParameterValues("dataBody");
		String[] isLists = request.getParameterValues("isList");
		String[] isEdits = request.getParameterValues("isEdit");
		
		if(ArrayUtils.isNotEmpty(ids) && !StringUtils.isEmpty(pageId)){
			PageSet pageSet = null;
			for(int i=0;i<ids.length;i++){
				pageSet = new PageSet();
				pageSet.setId(ids[i]);
				pageSet.setColumnName(columnNames[i]);
				pageSet.setAttributeName(attributeNames[i]);
				pageSet.setShowName(showNames[i]);
				pageSet.setFiledType(filedTypes[i]);
				pageSet.setWidth(widths[i]);
				pageSet.setIsQuery(isQuerys[i]);
				pageSet.setDataBody(dataBodys[i]);
				pageSet.setDataType(dataTypes[i]);
				pageSet.setPageId(pageId);
				pageSet.setCreateTime(new Date());
				pageSet.setUpdateTime(new Date());
				pageSet.setIsList(isLists[i]);
				pageSet.setIsEdit(isEdits[i]);
				setList.add(pageSet);
			}
		}
		return setList;
	}
	

}
