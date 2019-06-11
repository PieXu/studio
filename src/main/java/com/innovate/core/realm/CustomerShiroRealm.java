package com.innovate.core.realm;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovate.basic.exception.AccountExisitException;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.user.user.model.User;
import com.innovate.user.user.service.IUserService;
import com.innovate.util.CommonCons;
import com.innovate.util.DateUtils;
import com.innovate.util.LoggerUtils;

/**
 * hiro 认证 + 授权 自定义
 * 
 * @author IvanHsu
 * @2018年3月23日 下午2:10:20
 */
public class CustomerShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	@Autowired
	private DicUtil dicUtil;
	@Autowired
	private SessionDAO sessionDao;

	/**
	 * 认证信息，主要针对用户登录，
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 将AuthenticationToken 强转成 UsernamePasswordToken
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 2. 从UsernamePasswordToken 中来获取loginName
		String loginName = token.getUsername();
		
		if(checkIsLogon(loginName)){
			User userLogin = userService.queryUserByLoginName(loginName);
			throw new AccountExisitException("该账号于 "+DateUtils.getCustomDateString( userLogin.getLastLoginTime(), "yyyy-MM-dd HH:mm:ss") + " 登录<br/>登录IP地址为： "+userLogin.getLastLoginIp());
		}else{
			// 查询有误对应loginname的用户信息
			User userLogin = userService.queryUserByLoginName(loginName);
			LoggerUtils.debug(this.getClass(), "调用数据库的方法，从数据库中查询loginName对应的用户记录");
			// 4. 若用户不存在，则可以抛出 UnknownAccoountException 异常
			Dic dic = dicUtil.getUserStatusEnable();
			if (null == userLogin) {
				throw new UnknownAccountException("帐号不存在");
			} else if (null == dic || !dic.getId().equals(userLogin.getStatus())) {
				throw new DisabledAccountException("帐号已经禁止登录！");
			} else {
				String credentials = userLogin.getPassword(); // credentials：密码
				String realmName = getName();
				String salt = userLogin.getSalt();
				ByteSource credentialsSalt = ByteSource.Util.bytes(salt);// 这里的参数要给个唯一的;
				return new SimpleAuthenticationInfo(userLogin, credentials, credentialsSalt, realmName);
			}
		}
	}
	
	/**
	 * 主要用于权限的认证 如果不用注解 基本上用不到
	 * 1、subject.hasRole(“admin”) 或subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
	 * 2、@RequiresRoles("admin") ：在方法上加注解的时候； 
	 * 3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		System.out.println("doGetAuthorizationInfo");
		return null;
	}
	
	/**
	 * 检查当前是否用户已经登录了
	 * @param loginName
	 * @return
	 */
	private boolean checkIsLogon(String loginName)
	{
		Boolean bol = false;
		if(!"admin".equals(loginName))
		{
			Collection<Session> sessions = sessionDao.getActiveSessions();
			if(!sessions.isEmpty()){
				String existLoginName = null;
				for(Session session : sessions){
					//System.out.println("session login name ========================== "+session.getAttribute(Constants.SESSION_USER_LOGIN_NAME_KEY));
					existLoginName = (String) session.getAttribute(CommonCons.SESSION_USER_LOGIN_NAME_KEY);
					if(null != existLoginName && loginName.equals(existLoginName)){
						bol = true;
						break;
					}
				}
			}
		}
		return bol;
	}
	
	/**
	 * 清空当前用户权限信息
	 */
	public void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}


}
