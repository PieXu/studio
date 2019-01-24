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
 * @desc:自定义Select标签
 * @time: 2017年6月21日 上午9:29:28
 * @author IvanHsu
 */
public class SelectTag extends BodyTagSupport {

	private static final long serialVersionUID = -3992077344046712978L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<Dic> dicList;
	private String name;
	private String styleClass;
	private String selectedVal;
	private String defaultOption;

	@Override
	public int doStartTag() throws JspException
	{
		return EVAL_BODY_BUFFERED;
	}
	
	@Override  
    public int doEndTag() throws JspException {  
        StringBuilder builder = new StringBuilder();  
        
        builder.append("<select size=\"1\" name=\"")
        	   .append(name)
        	   .append("\" class=\"");
        if(!StringUtils.isEmpty(styleClass)){
    		builder.append(styleClass); 
        }
        builder.append("\" >");
        //默认显示 如:请选择...
        if(!StringUtils.isEmpty(defaultOption)){
        	 builder.append("<option value=\"\">"); 
        	 builder.append(defaultOption);
        	 builder.append("</option>");
        }
        for(Dic dic : dicList){
	        builder.append("<option value=\""); 
	        builder.append(dic.getId());
	        builder.append("\" ");  
	        if(selectedVal.equals(dic.getId()))
	        	 builder.append(" selected"); 
	        builder.append(" >"); 
	        builder.append(dic.getName());  
	        builder.append(" </option>");  
		}  
        builder.append(" </select>");
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

	public String getDefaultOption()
	{
		return defaultOption;
	}

	public void setDefaultOption(String defaultOption)
	{
		this.defaultOption = defaultOption;
	} 
}
