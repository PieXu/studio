package com.innovate.basic.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 基于@ExceptionHandler异常处理
	 * @param request
	 * @param ex
	 * @return
	 */
    @ExceptionHandler  
    @ResponseBody
    public void processException(HttpServletRequest request, Exception ex, HttpServletResponse response) 
    {  
    	logger.error("系统运行异常：{}，异常的类：{}",  ex.getMessage(), ex.getClass());;
        try {
        	StringBuffer script = new StringBuffer();
        	script.append("parent.layer.msg('系统运行异常,请联系管理员<br/>异常信息：");
        	script.append(ex.getMessage());
        	script.append("',{ time: 0,shade:0.3,btn:['知道了'],yes:function(index){parent.layer.closeAll();}});");
			response.getWriter().write("<script>"+script.toString()+"</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }   
}
