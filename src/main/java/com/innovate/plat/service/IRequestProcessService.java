package com.innovate.plat.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.plat.model.RequestProcess;

public interface IRequestProcessService extends IBaseService<RequestProcess>{

	public Page<LinkedHashMap<String, Object>> getObjectPage(RequestProcess req);
	
	public void updateObject(Map<String,Object> params);
	
	public LinkedHashMap<String,Object> getObject(Map<String,Object> params);

	public void saveOrUpdate(Map<String, Object> saveMap, String pageId , String tableName);

	public void delete(String _tableName, Map<String, Object> params);
}
