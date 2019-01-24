package com.innovate.basic.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.innovate.sys.dic.model.Dic;

/**
 * @desc:自定义RadioTag标签
 * @package:com.xu.base
 * @time: 2017年6月21日 上午9:29:28
 * @author IvanHsu
 */
public class RadioTag extends BodyTagSupport {

	private static final long serialVersionUID = -3992077344046712978L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String STYLE_CLASS= "radio-box";
	
	private List<Dic> dicList;
	private String name;
	private String styleClass;
	private String selectedVal;

	@Override
	public int doStartTag() throws JspException
	{
		return EVAL_BODY_BUFFERED;
	}
	
	@Override  
    public int doEndTag() throws JspException {  
        StringBuilder builder = new StringBuilder();  

        for(Dic dic : dicList){
	        builder.append("<div class=\" "); 
	        if(!StringUtils.isEmpty(styleClass)){
        		builder.append(styleClass); 
	        }else{
	        	builder.append(STYLE_CLASS); 
	        }
	        builder.append("\"> "); 
	        
	        builder.append("<input type=\"radio\" value=\"");
	        builder.append(dic.getId());
	        builder.append("\" name=\"");  
	        builder.append(name);  
	        builder.append("\" id=\"radio-");  
	        builder.append(dic.getId());  
	        builder.append("\" ");  
	        if(!StringUtils.isEmpty(selectedVal) && selectedVal.equals(dic.getId()))
	        	 builder.append(" checked=\"true\" "); 
	        builder.append(" /> "); 
	        builder.append(" <label for=\"radio-");
	        builder.append(dic.getId());
	        builder.append("\">");  
	        builder.append(dic.getName()); 
	        builder.append(" </label>");  
	        builder.append(" </div>");  
		}  
        try {  
            pageContext.getOut().print(builder.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.error("checkbox标签初始化错误，请检查参数是否正确",e);  
        }  
        return EVAL_PAGE;  
    }

	public Logger getLogger()
	{
		return logger;
	}

	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}

	public List<Dic> getDicList()
	{
		return dicList;
	}

	public void setDicList(List<Dic> dicList)
	{
		this.dicList = dicList;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStyleClass()
	{
		return styleClass;
	}

	public void setStyleClass(String styleClass)
	{
		this.styleClass = styleClass;
	}

	public String getSelectedVal()
	{
		return selectedVal;
	}

	public void setSelectedVal(String selectedVal)
	{
		this.selectedVal = selectedVal;
	}  
}
