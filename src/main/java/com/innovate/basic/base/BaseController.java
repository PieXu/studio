package com.innovate.basic.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常处理
 * Spring MVC处理异常有3种方式： 
	（1）使用Spring MVC提供的简单异常处理器SimpleMappingExceptionResolver； 
	（2）实现Spring的异常处理接口HandlerExceptionResolver 自定义自己的异常处理器； 
	（3）使用@ExceptionHandler注解实现异常处理； 
 * @author IvanHsu
 * @2018年3月24日 下午5:23:05
 */
public class BaseController {
	/**
	 * 基于@ExceptionHandler异常处理
	 * @param request
	 * @param ex
	 * @return
	 */
    @ExceptionHandler  
    public String processException(HttpServletRequest request, Exception ex) 
    {  
    	ex.printStackTrace();
        request.setAttribute("exDesc", ex.getMessage()) ;
        request.setAttribute("exClass", ex.getClass()) ;
        return "error";   
    }   
}
