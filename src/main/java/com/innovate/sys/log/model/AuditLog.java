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
public class AuditLog extends BaseModel {

	@Invisible
	public static final String TAB_NAME = "t_audit_log";
	@Invisible
	private static final long serialVersionUID = -521190377028626676L;

	private String className;
	private String methodName;
	private String visitorName;
	private Date visitDate;
	private String visitResult;
	private String visitIp;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
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

}
