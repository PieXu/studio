package com.innovate.plat.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.innovate.basic.base.BaseController;
import com.innovate.plat.model.PageDefine;
import com.innovate.plat.model.PageSet;
import com.innovate.plat.model.RequestProcess;
import com.innovate.plat.service.IPageDefineService;
import com.innovate.plat.service.IRequestProcessService;
import com.innovate.user.user.controller.UserController;
import com.innovate.util.CommonCons;
import com.innovate.util.DateUtils;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SqlUtils;

/**
 * 
* <p>Title: PageController</p>
* <p>Description: 页面访问和处理</p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月16日
 */
@Controller
@RequestMapping("process")
public class RequestProcessController extends BaseController{

	@Autowired
	private IPageDefineService pageDefineservice;
	
	@Autowired
	private IRequestProcessService processService;
	
	/**
	* <p>Title: processList</p>
	* <p>Description: 列表请求处理</p>
	* @param request
	* @param response
	* @param model
	* @param code
	* @return
	 */
	@RequestMapping("/processList")
	public String processList(HttpServletRequest request,HttpServletResponse response,
			Model model,String code,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		if(StringUtils.isNotBlank(code)){
			PageDefine pageDefine = pageDefineservice.getPageDefineByCode(code,CommonCons.STATUS_NORMAL);
			
			/*
			 * 1、查询定义
			 */
			if(null!=pageDefine){
				List<PageSet> setList =pageDefineservice.getPageSetByPageId(pageDefine.getId());
				List<PageSet> filedList = new ArrayList<PageSet>();
				List<PageSet> queryList = new ArrayList<PageSet>();
				List<String> keyList = new ArrayList<String>();
				Map<String,Object> conditions = this.initQueryMap(request,keyList);
				Map<String,Object> params = new HashMap<String,Object>();
				StringBuilder sqlWhere = new StringBuilder(" where del_flag =#{params.delFlag}");
				if(null!=pageDefine.getSqlWhere() && StringUtils.isNotBlank(pageDefine.getSqlWhere())){
					sqlWhere.append( "and " + pageDefine.getSqlWhere());
				}
				for(PageSet set : setList)
				{
					//判断是不是在列表显示
					if(StringUtils.isNotBlank(set.getIsList()) && !"noshow".equalsIgnoreCase(set.getIsList())){
						filedList.add(set);
					}
					if("Y".equalsIgnoreCase(set.getIsQuery())){
						queryList.add(set);
						if("date".equalsIgnoreCase(set.getDataType())){
							String startDateParam = set.getAttributeName()+"Start";
							String endDateParam = set.getAttributeName()+"End";
							keyList.add(startDateParam);
							keyList.add(endDateParam);
							String startDateStr = request.getParameter(startDateParam);
							String endDateStr = request.getParameter(endDateParam);
							if(StringUtils.isNoneBlank(startDateStr)){
								sqlWhere.append(" and unix_timestamp(").append(set.getColumnName()).append(") >= unix_timestamp(#{params.")
								.append(startDateParam)
								.append("}) ");
								params.put(startDateParam, DateUtils.praseDate(startDateStr+" 00:00:00",null));
								conditions.put(startDateParam,startDateStr);
							}
							if(StringUtils.isNoneBlank(endDateStr)){
								sqlWhere.append(" and unix_timestamp(").append(set.getColumnName()).append(") <= unix_timestamp(#{params.")
								.append(endDateParam)
								.append("}) ");
								params.put(endDateParam,DateUtils.praseDate(endDateStr+" 23:59:59",null));
								conditions.put(endDateParam,endDateStr);
							}
							
						}else if("text".equalsIgnoreCase(set.getDataType())){
							keyList.add(set.getAttributeName());
							//查询条件
							String object = request.getParameter(set.getAttributeName());
							if(null != object && StringUtils.isNotBlank(object)){
								//SQL转义一下,text 模糊查询
								conditions.put(set.getAttributeName(), object);
								object = SqlUtils.escape(object, true, true);
								sqlWhere.append(" and ").append(set.getColumnName()).append(" like #{params.")
								.append(set.getAttributeName())
								.append("}");
								params.put(set.getAttributeName(), object);
							}
						}else{
							keyList.add(set.getAttributeName());
							//查询条件
							String object = request.getParameter(set.getAttributeName());
							if(null != object && StringUtils.isNotBlank(object)){
								sqlWhere.append(" and ").append(set.getColumnName()).append(" = #{params.")
								.append(set.getAttributeName())
								.append("}");
								params.put(set.getAttributeName(), object);
								conditions.put(set.getAttributeName(), object);
							}
						}
					}
				}
				model.addAttribute("filedList", filedList);
				model.addAttribute("queryList", queryList);
				/*
				 * 2.查询条件
				 */
				
				model.addAttribute("conditions", conditions);
				/*
				 * 3、查询SQL处理
				 */
				RequestProcess	req = new RequestProcess();
				req.setPageNum(pageNum);
				req.setPageSize(CommonCons.DEFAULT_PAGE_SIZE);
				
				params.put("tabelName", pageDefine.getTableName());
				params.put("sqlBody", pageDefine.getSqlBody());
				params.put("sqlWhere", sqlWhere.toString());
				params.put("delFlag", CommonCons.STATUS_NORMAL);
				req.setParams(params);
				Page<LinkedHashMap<String, Object>> page = processService.getObjectPage(req);
				model.addAttribute("page", page);
				model.addAttribute("pageDefine", pageDefine);
			}
			return "common/commonList";
		}
		return "common/error";
	}
	
	/**
	 * 
	* <p>Title: deleteUser</p>
	* <p>Description: 通用删除操作(逻辑删除，设置删除状态)</p>
	* @param request
	* @param model
	* @param id
	* @param _tableName
	* @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public ResultObject deleteUser(HttpServletRequest request,Model model,String id,String _tableName)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id) && StringUtils.isNotBlank(_tableName)){
			try{
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("delFlag", CommonCons.STATUS_DELETE);
				params.put("id", id);
				processService.delete(_tableName,params);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("删除操作成功  √");
			}catch (Exception e) {
				LoggerUtils.error(this.getClass(), e.getMessage());
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("删除操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数配置不正确");
		}
		return result;
	}
	
	/**
	 *通用 修改 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @param _tableName
	 * @param pageId
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"/add","/edit"})
	public String addOrEdit(HttpServletRequest request,Model model,String id,String _pageId) 
			throws Exception
	{
		if(StringUtils.isNotBlank(_pageId) ){
			PageDefine pageDefine = pageDefineservice.getById(_pageId);
			List<PageSet> setList =pageDefineservice.getPageSetByPageId(_pageId);
			if(!setList.isEmpty())
			{
				List<PageSet> filedList = new ArrayList<PageSet>();
				Map<String,Object> params = new HashMap<String,Object>();
				for(PageSet set : setList)
				{
					if(StringUtils.isNotBlank(set.getIsEdit()) && !"noshow".equalsIgnoreCase(set.getIsEdit())){
						filedList.add(set);
					}
				}
				if(StringUtils.isNotBlank(id)){
					StringBuilder sqlWhere = new StringBuilder(" where del_flag =#{params.delFlag} and id =#{params.id}");
					params.put("sqlBody", pageDefine.getSqlBody());
					params.put("sqlWhere", sqlWhere.toString());
					params.put("delFlag", CommonCons.STATUS_NORMAL);
					params.put("id",id);
					LinkedHashMap<String, Object> bizObject = processService.getObject(params);
					model.addAttribute("bizObject", bizObject);
				}
				model.addAttribute("filedList", filedList);
				model.addAttribute("def", pageDefine);
			}
		}
		return "common/nsm/commonEdit";
	}
	
	/**
	 * 
	* <p>Title: saveObject</p>
	* <p>Description: 通用保存 或者 更新</p>
	* @param request
	* @param response
	* @return
	 */
	@ResponseBody
	@RequestMapping(value={"/saveOrUpdate"})
	public ResultObject saveOrUpdate(HttpServletRequest request,HttpServletResponse response)
	{
		ResultObject result = new ResultObject();
		try {
			String pageId = request.getParameter("_pageId");
			String tableName = request.getParameter("_tableName");
			Map<String, Object> saveMap = initSaveMap(request);
			if(!saveMap.isEmpty()){
				processService.saveOrUpdate(saveMap,pageId,tableName);
			}
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
	* <p>Title: initQueryMap</p>
	* <p>Description: 查询参数</p>
	* @param request
	* @param keyList
	* @return
	 */
	private Map<String, Object> initQueryMap(HttpServletRequest request, List<String> keyList) {
		Map<String, Object> conditions = new HashMap<String,Object>();
		if(!keyList.isEmpty()){
			for(String key : keyList){
				conditions.put(key, request.getParameter(key));
			}
		}
		return conditions;
	}
	
	private Map<String, Object> initSaveMap(HttpServletRequest request) 
	{
		Map<String, Object> saveMap = new HashMap<String, Object>();
		Enumeration<String> attributeNames = request.getParameterNames();
		while (attributeNames.hasMoreElements()) {
			String name = attributeNames.nextElement();// 调用nextElement方法获得元素
			if(!name.startsWith("_")){
				saveMap.put(name, request.getParameter(name));
			}
		}
		return saveMap;
	}
	
	
}
