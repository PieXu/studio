package com.innovate.sys.log.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.sys.log.dao.VisitLogDao;
import com.innovate.sys.log.model.VisitLog;
import com.innovate.sys.log.service.IVisitLogService;
import com.innovate.util.IdUtil;

/**
 * 访问日志
 * @author IvanHsu
 * @2018年3月31日 下午5:03:34
 */
@Service("log.service.VisitLogServiceImpl")
public class VisitLogServiceImpl implements IVisitLogService{

	@Autowired
	private VisitLogDao visitLogDao;
	
	@Override
	public void saveVisitLog(VisitLog visitLog) {
		if(null!=visitLog){
			if(StringUtils.isEmpty(visitLog.getId())){
				visitLog.setId(IdUtil.getUUID());
			}
			visitLog.setCreateTime(new Date());
			visitLog.setUpdateTime(new Date());
			visitLogDao.saveVisitLog(visitLog);
		}
	}

	/**
	 * 分也查询
	 */
	@Override
	public Page<VisitLog> pageVisitLog(VisitLog log) {
		return visitLogDao.pageVisitLog(log);
	}

	@Override
	public int countVisitTimesByType(String userId, String type) {
		if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(type)){
			return visitLogDao.countVisitTimesByType(userId,type);
		}
		return 0;
	}

	/**
	 * 首页的登录分析查询
	 */
	@Override
	public List<Map<String, Object>>  countVisitLog() {
		return visitLogDao.countVisitLog();
	}

}
