package com.innovate.plat.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.plat.dao.RequestProcessDao;
import com.innovate.plat.model.PageSet;
import com.innovate.plat.model.RequestProcess;
import com.innovate.plat.service.IPageDefineService;
import com.innovate.plat.service.IRequestProcessService;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;

/**
 * 
* <p>Title: RequestProcessServiceImpl</p>
* <p>Description: 定义处理请求</p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月17日
 */
@Service
public class RequestProcessServiceImpl implements IRequestProcessService{

	@Autowired
	private RequestProcessDao processDao;
	@Autowired
	private IPageDefineService pageDefServ;

	/*
	 * （非 Javadoc）
	* Title: getObjectPage
	* Description: 
	* @param req
	* @return
	* @see com.innovate.plat.service.IRequestProcessService#getObjectPage(com.innovate.plat.model.RequestProcess)
	 */
	@Override
	public Page<LinkedHashMap<String, Object>> getObjectPage(RequestProcess req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return processDao.superProcessSelect(req.getParams());
	}

	/*
	 * （非 Javadoc）
	* Title: updateObject
	* Description: 
	* @param params
	* @see com.innovate.plat.service.IRequestProcessService#updateObject(java.util.Map)
	 */
	@Override
	public void updateObject(Map<String, Object> params) {
//		processDao.(params);
		
	}

	/*
	 * （非 Javadoc）
	* Title: getObject
	* Description: 
	* @param params
	* @return
	* @see com.innovate.plat.service.IRequestProcessService#getObject(java.util.Map)
	 */
	@Override
	public LinkedHashMap<String, Object> getObject(Map<String,Object> params ) {
		return processDao.getObject(params);
	}

	/*
	 * （非 Javadoc）
	* Title: saveOrUpdate
	* Description: 
	* @param saveMap
	* @param tableName
	* @see com.innovate.plat.service.IRequestProcessService#saveOrUpdate(java.util.Map, java.lang.String)
	 */
	@Override
	public void saveOrUpdate(Map<String, Object> saveMap, String pageId , String tableName) {
		String id = (String) saveMap.get("id");
		List<PageSet> sets = pageDefServ.getPageSetByPageId(pageId);
		if(StringUtils.isNotBlank(id)){ // 更新
			StringBuffer updateSql = new StringBuffer("update ").append(tableName).append(" set _updateSets where id = #{params.id}");
			String ups = "update_time=#{params.updateTime}";
			for(PageSet set :sets)
			{
				if(saveMap.containsKey(set.getAttributeName())){//页面设置的参数
					ups += ","+set.getColumnName()+"=#{params."+set.getAttributeName()+"}";
				}
			}
			saveMap.put("updateTime", new Date());
			String sql = updateSql.toString().replaceAll("_updateSets", ups);
			processDao.updateObject(saveMap, sql);
		}else{ //无id的时候保存
			StringBuffer insertSQl = new StringBuffer("insert into ").append(tableName).append(" ( _columns ) values ( _vals )");
			/*
			 * 默认条件的拼接 最好在页面上不显示隐藏
			 */
			String columus = "create_time,update_time,del_flag";
			String values = "#{params.createTime},#{params.updateTime},#{params.delFlag}";
			for(PageSet set :sets)
			{
				if(saveMap.containsKey(set.getAttributeName())){//页面设置的参数
					columus += ","+set.getColumnName();
					values += ",#{params."+set.getAttributeName()+"}";
				}
			}
			saveMap.put("id", IdUtil.getUUID());
			saveMap.put("createTime", new Date());
			saveMap.put("updateTime", new Date());
			saveMap.put("delFlag", CommonCons.STATUS_NORMAL);
			String sql = insertSQl.toString().replaceAll("_columns", columus).replaceAll("_vals", values);
			processDao.saveObject(saveMap, sql);
		}
	}

	@Override
	public void delete(String _tableName, Map<String, Object> params) {
		StringBuffer updateSQL = new StringBuffer("update ")
				.append(_tableName)
				.append(" set del_flag = #{params.delFlag} where id = #{params.id} ");
		processDao.deleteObject(updateSQL.toString(),params);
	}

}
