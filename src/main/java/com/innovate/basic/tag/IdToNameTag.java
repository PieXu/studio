package com.innovate.basic.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.util.StringUtils;

import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.dic.service.impl.DicFactory;
import com.innovate.util.LoggerUtils;

public class IdToNameTag  extends BodyTagSupport{
	
	private static final long serialVersionUID = 1807771045439363177L;

	private DicUtil dicUtil =DicFactory.getDicUtil() ;
	
	private String dicId;

	@Override
	public int doStartTag() throws JspException
	{
		return EVAL_BODY_BUFFERED;
	}
	
	@Override  
    public int doEndTag() throws JspException 
	{
        if(!StringUtils.isEmpty(dicId)){
        	Dic dic = dicUtil.getDicInfo(dicId);
        	if(null != dic){
        		try {
					pageContext.getOut().print(dic.getName());
				} catch (IOException e) {
		            LoggerUtils.error(IdToNameTag.class,"idToName标签初始化错误，请检查参数是否正确",e);  
				}  
        	}
        }
        return EVAL_PAGE;  
	}
	
	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}
	
}
