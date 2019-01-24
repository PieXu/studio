package com.innovate.sys.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.sys.security.model.OnlineUser;
import com.innovate.sys.security.service.ISecurityService;
import com.innovate.util.CommonCons;

/**
 * 系统验证安全Service
 * @author IvanHsu
 * @2018年4月2日 上午10:17:52
 */
@Service("security.service.SecurityServiceImpl")
public class SecurityServiceImpl implements ISecurityService{

	@Autowired
	private SessionDAO sessionDao;
	
	/**
	 * 在线用户查询
	 */
	@Override
	public List<?> loadAllOnlineUser() {
		List<OnlineUser> onLineUserlist = new ArrayList<OnlineUser>();
		Collection<Session> sessions = sessionDao.getActiveSessions();
		if(!sessions.isEmpty()){
			for(Session session : sessions){
				if(null!=session && StringUtils.isNotEmpty((String)(session.getAttribute(CommonCons.SESSION_USER_LOGIN_NAME_KEY)))){
					OnlineUser onlineUser = new OnlineUser();
					onlineUser.setSessionId(session.getId().toString());
					onlineUser.setHost(session.getHost());
					onlineUser.setLastAccessDate(session.getLastAccessTime());
					onlineUser.setStartDate(session.getStartTimestamp());
					onlineUser.setLoginUser((String)session.getAttribute(CommonCons.SESSION_USER_LOGIN_NAME_KEY));
					onLineUserlist.add(onlineUser);
				}
			}
		}
		return onLineUserlist;
	}

	/**
	 * 踢出用户
	 */
	@Override
	public String kickoutUser(String sessionId) 
	{
		String userName = "";
		Collection<Session> sessions = sessionDao.getActiveSessions();
		if(!sessions.isEmpty()){
			for(Session session : sessions){
				if(null!=session && StringUtils.equals(session.getId().toString(), sessionId)){
					userName = (String)session.getAttribute(CommonCons.SESSION_USER_LOGIN_NAME_KEY);
					session.setTimeout(0);
					sessionDao.delete(session);
					break;
				}
			}
		}
		return userName;
	}

}
