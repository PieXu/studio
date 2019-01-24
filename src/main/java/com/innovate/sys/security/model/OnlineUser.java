package com.innovate.sys.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 在线用户
 * 
 * @author IvanHsu
 * @2018年4月2日 上午10:21:08
 */
public class OnlineUser implements Serializable {

	private static final long serialVersionUID = -6675342065098141651L;

	private String sessionId;
	private String loginUser;
	private Date lastAccessDate;
	private Date startDate;
	private String host;

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
