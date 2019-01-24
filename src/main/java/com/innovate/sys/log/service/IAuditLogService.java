package com.innovate.sys.log.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.sys.log.model.AuditLog;

public interface IAuditLogService extends IBaseService{

	/**
	 * 审计操作日志保存
	 * @param autidLog
	 */
	public void saveAuditLog(AuditLog autidLog);
	
	/**
	 * 访问日志的列表 分页查询
	 * @param log
	 * @return
	 */
	public Page<AuditLog> pageAuditLog(AuditLog autidLog);


}
