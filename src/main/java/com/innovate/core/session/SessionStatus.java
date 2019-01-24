package com.innovate.core.session;

import java.io.Serializable;

/**
 * 
 * 开发公司：SOJSON在线工具
 * <p>
 * 版权所有：© www.sojson.com
 * <p>
 * 博客地址：http://www.sojson.com/blog/
 * <p>
 * <p>
 * 
 * Session 状态 VO
 * 
 * <p>
 * 
 * 区分 责任人 日期 说明<br/>
 * 创建 周柏成 2016年6月2日 <br/>
 *
 * @author zhou-baicheng
 * @email so@sojson.com
 * @version 1.0,2016年6月2日 <br/>
 * 
 */
/**
 * Session的会话状态，自定义
 * @author IvanHsu
 * @2018年3月23日 下午1:18:13
 */
public class SessionStatus implements Serializable 
{

	private static final long serialVersionUID = -5221303752699973633L;
	// 是否踢出 true:有效，false：踢出。
	private Boolean onlineStatus = Boolean.TRUE;

	public Boolean isOnlineStatus() {
		return onlineStatus;
	}

	public Boolean getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Boolean onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

}
