package com.innovate.core.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovate.sys.log.model.AuditLog;
import com.innovate.sys.log.service.IAuditLogService;
import com.innovate.user.user.model.User;
import com.innovate.util.LoggerUtils;

/**
 * 操作日志记录切面
 * 
 * @author IvanHsu
 * @2018年3月31日 下午5:10:15
 */
@Component
public class AuditLogAspect {

	@Autowired
	private IAuditLogService auditLogService;

	/**
	 * 访问成功之后
	 * @param joinPoint
	 */
	public void doAuditSucccess(JoinPoint joinPoint) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if(null != subject){
				Session session = subject.getSession();
				User currentUser = (User) subject.getPrincipal();
				if(null!=currentUser){
					// 获取目标类名
					String calssName = joinPoint.getTarget().getClass().getName();
					// 获取方法名
					String methodName = joinPoint.getSignature().getName();
					Object[] arguments = joinPoint.getArgs();
					AuditLog autidLog = new AuditLog();
					autidLog.setVisitIp(session.getHost());
					autidLog.setComments(arguments.toString());
					autidLog.setVisitDate(session.getLastAccessTime());
					autidLog.setVisitorName(currentUser.getUserName());
					autidLog.setClassName(calssName);
					autidLog.setMethodName(methodName);
					autidLog.setVisitResult("成功");
					auditLogService.saveAuditLog(autidLog);
				}
			}
		} catch (Exception e) {
			LoggerUtils.error(AuditLogAspect.class, e.getMessage());
		}
	}

	/**
	 * 访问异常之后记录的日志信息
	 * @param joinPoint
	 * @param e
	 */
	public void doAuditException(JoinPoint joinPoint, Throwable e) 
	{
		try {
			Subject subject = SecurityUtils.getSubject();
			if(null != subject){
				Session session = subject.getSession();
				User currentUser = (User) subject.getPrincipal();
				// 获取目标类名
				String calssName = joinPoint.getTarget().getClass().getName();
				// 获取方法名
				String methodName = joinPoint.getSignature().getName();
				Object[] arguments = joinPoint.getArgs();
				AuditLog autidLog = new AuditLog();
				autidLog.setVisitIp(session.getHost());
				autidLog.setComments(arguments.toString());
				autidLog.setVisitDate(session.getLastAccessTime());
				autidLog.setVisitorName(currentUser.getUserName());
				autidLog.setClassName(calssName);
				autidLog.setMethodName(methodName);
				autidLog.setVisitResult("失败");
				auditLogService.saveAuditLog(autidLog);
			}
		} catch (Exception ex) {
			LoggerUtils.error(AuditLogAspect.class, e.getMessage());
		}
	}

}
