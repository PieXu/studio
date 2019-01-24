package com.innovate.automate.code.model;

import java.io.Serializable;

/**
 * 代码生成的设置信息
 * 
 * @author IvanHsu
 * @2018年4月13日 上午11:32:12
 */
public class CodeGenSetting implements Serializable {

	private static final long serialVersionUID = -4752284352799581208L;

	private String targetProject;
	private String dbUrl;
	private String dbUser;
	private String dbPass;
	private String dbDriver;
	private String workPath;

	public String getTargetProject() {
		return targetProject;
	}

	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getWorkPath() {
		return workPath;
	}

	public void setWorkPath(String workPath) {
		this.workPath = workPath;
	}

}
