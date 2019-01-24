package com.innovate.core.interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovate.basic.annotation.SystemVisitLog;
import com.innovate.sys.log.model.VisitLog;
import com.innovate.sys.log.service.IVisitLogService;
import com.innovate.user.user.model.User;
import com.innovate.util.LoggerUtils;

/**
 * 操作日志记录切面
 * 
 * @author IvanHsu
 * @2018年3月31日 下午5:10:15
 */
@Component
public class VisitLogAspect {

	@Autowired
	private IVisitLogService visitLogService;

	/**
	 * 退出操作记录日志
	 * @param joinPoint
	 */
	public void logoutBefore(JoinPoint joinPoint)
	{
		Subject subject = SecurityUtils.getSubject();
		if(null != subject){
			Session session = subject.getSession();
			User currentUser = (User) subject.getPrincipal();
			if(null!=currentUser){
				VisitLog visitLog = new VisitLog();
				visitLog.setSessionId(session.getId().toString());
				visitLog.setVisitIp(session.getHost());
				visitLog.setComments("用户退出");
				visitLog.setVisitDate(new Date());
				visitLog.setVisitName(currentUser.getUserName());
				visitLog.setVisitType("退出");
				visitLog.setVisitResult("成功");
				visitLog.setUserId(currentUser.getId());
				visitLogService.saveVisitLog(visitLog);
			}
		}
	}
	
	/**
	 * 访问成功之后
	 * @param joinPoint
	 */
	public void doVisitSucccess(JoinPoint joinPoint) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if(null != subject){
				Session session = subject.getSession();
				User currentUser = (User) subject.getPrincipal();
				if(null!=currentUser){
					String comments = this.getDescription(joinPoint);
					String visitType = this.getVisitType(joinPoint);
					VisitLog visitLog = new VisitLog();
					visitLog.setSessionId(session.getId().toString());
					visitLog.setVisitIp(session.getHost());
					visitLog.setComments(comments);
					visitLog.setVisitDate(new Date());
					visitLog.setVisitName(currentUser.getUserName());
					visitLog.setVisitType(visitType);
					visitLog.setVisitResult("成功");
					visitLog.setUserId(currentUser.getId());
					visitLogService.saveVisitLog(visitLog);
				}
			}
		} catch (Exception e) {
			LoggerUtils.error(VisitLogAspect.class, e.getMessage());
		}
	}

	/**
	 * 访问异常之后记录的日志信息
	 * @param joinPoint
	 * @param e
	 */
	public void doVisitedException(JoinPoint joinPoint, Throwable e) 
	{
		try {
			User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
			if(null != currentUser){
				String visitType = this.getVisitType(joinPoint);
				VisitLog visitLog = new VisitLog();
				visitLog.setComments(e.getMessage());
				visitLog.setVisitDate(new Date());
				visitLog.setVisitName(currentUser.getUserName());
				visitLog.setVisitType(visitType);
				visitLog.setVisitResult("失败");
				visitLog.setUserId(currentUser.getId());
				visitLogService.saveVisitLog(visitLog);
			}
		} catch (Exception ex) {
			LoggerUtils.error(VisitLogAspect.class, e.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String getDescription(JoinPoint joinPoint) throws Exception {
		// 获取目标类名
		String targetName = joinPoint.getTarget().getClass().getName();
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		// 获取相关参数
		Object[] arguments = joinPoint.getArgs();
		// 生成类对象
		Class targetClass = Class.forName(targetName);
		// 获取该类中的方法
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (!method.getName().equals(methodName)) {
				continue;
			}
			Class[] clazzs = method.getParameterTypes();
			if (clazzs.length != arguments.length) {// 比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
				continue;
			}
			description = method.getAnnotation(SystemVisitLog.class).description();
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String getVisitType(JoinPoint joinPoint) throws Exception {
		// 获取目标类名
		String targetName = joinPoint.getTarget().getClass().getName();
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		// 获取相关参数
		Object[] arguments = joinPoint.getArgs();
		// 生成类对象
		Class targetClass = Class.forName(targetName);
		// 获取该类中的方法
		Method[] methods = targetClass.getMethods();
		String visitType = "";
		for (Method method : methods) {
			if (!method.getName().equals(methodName)) {
				continue;
			}
			Class[] clazzs = method.getParameterTypes();
			if (clazzs.length != arguments.length) {// 比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
				continue;
			}
			visitType = method.getAnnotation(SystemVisitLog.class).visitType();
		}
		return visitType;
	}

}
