package com.innovate.basic.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class ValueToNameTag  extends BodyTagSupport{
	
	private static final long serialVersionUID = -328481488171497026L;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String value;
	private String array;

	@Override
	public int doStartTag() throws JspException
	{
		return EVAL_BODY_BUFFERED;
	}
	
	@Override  
    public int doEndTag() throws JspException 
	{
        if(!StringUtils.isEmpty(value) && !StringUtils.isEmpty(array)){
    		try {
    			//第一种方式  
    	        @SuppressWarnings("unchecked")
				Map<String,Object> jsonMap = (Map<String,Object>)JSON.parse(array);
    	        if(null!=jsonMap){
    	        	pageContext.getOut().print(jsonMap.get(value));
    	        }else{
    	        	 logger.error("ValueNameTag标签转换异常，请检查参数是否正确");  
    	        }
			} catch (IOException e) {
	            logger.error("ValueNameTag标签初始化错误，请检查参数是否正确",e);  
			}  
        }
        return EVAL_PAGE;  
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}
	
}
