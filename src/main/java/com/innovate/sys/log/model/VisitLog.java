package com.innovate.sys.log.model;

import java.util.Date;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * 访问日志
 * 
 * @author IvanHsu
 * @2018年3月31日 下午4:05:57
 */
public class VisitLog extends BaseModel {

	@Invisible
	public static final String TAB_NAME = "t_visit_log";
	@Invisible
	private static final long serialVersionUID = -521190377028626676L;

	private String visitName;
	private String sessionId;
	private String visitType;
	private Date visitDate;
	private String visitResult;
	private String visitIp;
	private String userId;

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitResult() {
		return visitResult;
	}

	public void setVisitResult(String visitResult) {
		this.visitResult = visitResult;
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
