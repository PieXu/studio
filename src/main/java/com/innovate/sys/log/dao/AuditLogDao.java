package com.innovate.sys.log.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.sys.log.model.AuditLog;

/**
 * 操作日志Dao
 * @author IvanHsu
 * @2018年3月31日 下午5:05:29
 */
@Repository("log.dao.AuditLogDao")
public interface AuditLogDao {

	/**
	 * 保存
	 * @param autidLog
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into "+AuditLog.TAB_NAME+" (#{autidLog})")
	public void saveAuditLog(AuditLog autidLog);

	
	/**
	 * 访问日志的列表 分页查询
	 * @param log
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+AuditLog.TAB_NAME + "  (#{autidLog}) order by visit_date desc ")
	public Page<AuditLog> pageAuditLog(AuditLog autidLog);

}
