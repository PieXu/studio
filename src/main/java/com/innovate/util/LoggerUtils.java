package com.innovate.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日志工具类
 * @author IvanHsu
 * @2018年3月23日 下午1:07:06
 */
public class LoggerUtils {
	
	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void debug(Class<? extends Object> clazz ,String message){
		Logger logger = Logger.getLogger(clazz);
		logger.debug(message);
	}
	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param fmtString 输出信息key
	 * @param value		输出信息value
	 */
	public static void fmtDebug(Class<? extends Object> clazz,String fmtString,Object...value){
		if(StringUtils.isBlank(fmtString)){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		debug(clazz, fmtString);
	}
	/**
	 * Error 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 * @param e			异常类
	 */
	public static void error(Class<? extends Object> clazz ,String message,Exception e){
		Logger logger = Logger.getLogger(clazz);
		if(null == e){
			logger.error(message);
			return ;
		}
		logger.error(message, e);
	}
	/**
	 * Error 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void error(Class<? extends Object> clazz ,String message){
		error(clazz, message, null);
	}
	/**
	 * 异常填充值输出
	 * @param clazz 	目标.Class
	 * @param fmtString	输出信息key
	 * @param e			异常类
	 * @param value		输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz,Exception e,String fmtString,Object...value){
		if(StringUtils.isBlank(fmtString)){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString, e);
	}
	/**
	 * 异常填充值输出
	 * @param clazz		目标.Class
	 * @param fmtString 输出信息key
	 * @param value		输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz,
			String fmtString, Object...value) {
		if(StringUtils.isBlank(fmtString)){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString);
	}
}
