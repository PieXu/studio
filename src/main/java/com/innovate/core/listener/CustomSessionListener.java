package com.innovate.core.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.innovate.core.session.ShiroSessionRepository;
import com.innovate.util.LoggerUtils;

public class CustomSessionListener implements SessionListener{

	private ShiroSessionRepository  shiroSessionRepository;
	
	/**
	 * 会话异常
	 */
	@Override
	public void onExpiration(Session session) {
		//session.get
		LoggerUtils.fmtError(CustomSessionListener.class,"CustomSessionListener 异常",session);
	}

	/**
	 * 会话开始
	 */
	@Override
	public void onStart(Session session) {
		LoggerUtils.fmtDebug(CustomSessionListener.class,"CustomSessionListener onStart",session);
	}

	/**
	 * 会话停止
	 */
	@Override
	public void onStop(Session session) {
		LoggerUtils.fmtDebug(CustomSessionListener.class,"CustomSessionListener onStop",session);
	}

	public ShiroSessionRepository getShiroSessionRepository() {
		return shiroSessionRepository;
	}

	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}

}
