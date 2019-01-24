package com.innovate.sys.log.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.sys.log.model.VisitLog;

/**
 * 
 * @author IvanHsu
 * @2018年3月31日 下午5:06:44
 */
public interface IVisitLogService  extends IBaseService{
	
	/**
	 * 保存日志
	 * @param visitLog
	 */
	public void saveVisitLog(VisitLog visitLog);

	/**
	 * 访问日志的列表 分页查询
	 * @param log
	 * @return
	 */
	public Page<VisitLog> pageVisitLog(VisitLog log);

	/**
	 * 按类型统计用户的访问次数
	 * @param id
	 * @param string
	 * @return
	 */
	public int countVisitTimesByType(String id, String string);

	/**
	 * 统计登录的信息
	 * @return
	 */
	public List<Map<String, Object>> countVisitLog();
	
}
