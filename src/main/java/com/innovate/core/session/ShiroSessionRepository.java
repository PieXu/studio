package com.innovate.core.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * session会话管理存储器
 * @author IvanHsu
 * @2018年3月23日 下午12:57:11
 */
public interface ShiroSessionRepository {

	/**
	 * 存储Session
	 * @param session
	 */
    void saveSession(Session session);
    /**
     * 删除session
     * @param sessionId
     */
    void deleteSession(Serializable sessionId);
    /**
     * 获取session
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);
    /**
     * 获取所有sessoin
     * @return
     */
    Collection<Session> getAllSessions();
}
