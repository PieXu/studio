package com.innovate.sys.log.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.sys.log.dao.AuditLogDao;
import com.innovate.sys.log.model.AuditLog;
import com.innovate.sys.log.service.IAuditLogService;
import com.innovate.util.IdUtil;

/**
 * 操作日志的记录
 * @author IvanHsu
 * @2018年3月31日 下午5:04:46
 */
@Service("log.service.AuditLogServiceImpl")
public class AuditLogServiceImpl implements IAuditLogService{

	@Autowired
	private AuditLogDao auditLogDao;
	
	@Override
	public void saveAuditLog(AuditLog autidLog) {
		if(null!=autidLog){
			if(StringUtils.isEmpty(autidLog.getId())){
				autidLog.setId(IdUtil.getUUID());
			}
			autidLog.setCreateTime(new Date());
			autidLog.setUpdateTime(new Date());
			auditLogDao.saveAuditLog(autidLog);
		}
		
	}

	/**
	 * 分也查询
	 */
	@Override
	public Page<AuditLog> pageAuditLog(AuditLog autidLog) {
		return auditLogDao.pageAuditLog(autidLog);
	}

}
