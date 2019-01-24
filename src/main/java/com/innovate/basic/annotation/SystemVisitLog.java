package com.innovate.basic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统的访问日志的注解类
 * @author IvanHsu
 * @2018年3月31日 下午4:41:10
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface SystemVisitLog {

	/**
	 * 描述
	 * @return
	 */
	public String description() default "";  
	
	/**
	 * 访问类型
	 * @return
	 */
	public String visitType() default "";
	
}
