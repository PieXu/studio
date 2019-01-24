package com.innovate.basic.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.util.StringUtils;

import com.innovate.sys.dic.model.Dic;
import com.innovate.util.LoggerUtils;

/**
 * @desc:自定义CheckBox标签
 * @time: 2017年6月21日 上午9:29:28
 * @author IvanHsu
 */
public class CheckTag extends BodyTagSupport {

	private static final long serialVersionUID = -3992077344046712978L;
	
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
	        builder.append("<label class=\" "); 
	        if(!StringUtils.isEmpty(styleClass)){
        		builder.append(styleClass); 
	        }
	        builder.append("\"> ");  
	        builder.append("<input type=\"checkbox\" value=\"");
	        builder.append(dic.getId());
	        builder.append("\" name=\"");  
	        builder.append(name);  
	        builder.append("\" ");  
	        if(selectedVal.contains(dic.getId()))
	        	 builder.append(" checked=\"true\" "); 
	        builder.append(" > "); 
	        builder.append(dic.getName());  
	        builder.append(" </label>&nbsp;&nbsp;");  
		}  
        try {  
            pageContext.getOut().print(builder.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
            LoggerUtils.fmtError(CheckTag.class, e,"checkbox标签初始化错误，请检查参数是否正确",e);
        }  
        return EVAL_PAGE;  
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
