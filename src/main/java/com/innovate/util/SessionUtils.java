package com.innovate.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.innovate.user.user.model.User;

/**
 * @time: 2017年6月19日 下午4:31:49
 * @author IvanHsu
 */
public class SessionUtils {

	/**
	 * session存储值
	 * 
	 * @param sessionKey
	 * @param sessionValue
	 */
	public static void setSessionAttribute(String sessionKey, Object sessionValue) 
	{
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			session.setAttribute(sessionKey, sessionValue);
		}
	}
	
	/**
	 * 获取session的属性值
	 * @param sessionKey
	 * @return
	 */
	public static Object getSessionAttribute(String sessionKey) 
	{
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			return session.getAttribute(sessionKey);
		}
		return null;
	}
	
	/**
	 * 获取session的属性值
	 * @param sessionKey
	 * @return
	 */
	public static void removeSessionAttribute(String sessionKey) 
	{
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			session.removeAttribute(sessionKey);
		}
	}

	/**
	 * 当前登录人的ID
	 * 
	 * @return
	 */
	public static String getCurrentUserId() {
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			User currentUser = (User) subject.getPrincipal();
			if (null != currentUser) {
				return currentUser.getId();
			}
		}
		return "";
	}
	
	/**
	 * 判断是否是超级用户
	* <p>Title: checkIsSuperUser</p>
	* <p>Description: </p>
	* @return
	 */
	public static boolean checkIsSuperUser()
	{
		Object sessionAttribute = SessionUtils.getSessionAttribute(CommonCons.SESSION_USER_KEY);
		if(null!=sessionAttribute){
			User user = (User) sessionAttribute;
			String isSuperUser = user.getIsSuperUser();
			return StringUtils.isNotBlank(isSuperUser) && CommonCons.Y_N_ENUM.Y.toString().equals(isSuperUser);
		}
		return false;
	}
}
