package com.innovate.basic.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.Page;

/**
 * @desc:自定义分页标签
 * @time: 2017年6月21日 上午9:29:28
 * @author IvanHsu
 * @param <E>
 */
public class PageTag<E> extends BodyTagSupport {

	private static final long serialVersionUID = -3992077344046712978L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String formId;
	private Page<E> page;;
	private String ajaxType;

	@Override
	public int doStartTag() throws JspException
	{
		return EVAL_BODY_BUFFERED;
	}
	
	@Override  
    public int doEndTag() throws JspException {  
        //标签体之间的内容  
        String bodyContentVal = null;  
        if (bodyContent != null) {  
            bodyContentVal = bodyContent.getString();  
        }  
        //如果页数小于0  
        if (page.getPages() <= 0) {  
        	page.setPageNum(0);  
        }  
		StringBuilder builder = new StringBuilder();  
		
	    builder.append("<div style=\"margin-top:10px; text-align: right;\"> "); 
	    builder.append("<span class=\"pagespan\">");
	    builder.append("<div class='pageDiv'>");  
	    if (Integer.valueOf(page.getPageNum()) > 1) {  
	        builder.append("<a class=\"pageClass\" onclick=\"_toPage(").append(1).append(")\">").append("  [首页]  ").append("</a>");  
	        builder.append("<a class=\"pageClass\"  onclick=\"_toPage(").append(page.getPageNum()-1).append(")\">").append("  [上一页]  ").append("</a>");  
	    } else {  
	        builder.append("<a class=\"pageClassDisable\">").append("  [首页]  ").append("</a>");  
	        builder.append("<a class=\"pageClassDisable\">").append("  [上一页]  ").append("</a>");  
	    }  
	    if (Integer.valueOf(page.getPageNum()) < Integer.valueOf(page.getPages())) {  
	        builder.append("<a class=\"pageClass\" onclick=\"_toPage(").append(page.getPageNum()+1).append(")\">").append("  [下一页]  ").append("</a>");  
	        builder.append("<a class=\"pageClass\" onclick=\"_toPage(").append(page.getPages()).append(")\">").append("  [尾页]  ").append("</a>");  
	    } else {  
	        builder.append("<a class=\"pageClassDisable\">").append("  [下一页]  ").append("</a>");  
	        builder.append("<a class=\"pageClassDisable\">").append("  [尾页]  ").append("</a>");  
	    }  
	    builder.append("<span class=\"pageSpan\">共").append(page.getPages()).append("页/").append(page.getTotal()).append("条 </span>  ");  
	    builder.append("<span class=\"pageSpan\">第 <input type='text' id=\"_pageNum\" name=\"_pageNum\" ");  
	    if ("0".equals(page.getPageNum())) {builder.append("disabled=\"disabled\"");  
	    }  
	    builder.append(" class=\"pageInput\" maxlength=\"8\" value='");  
	    builder.append(page.getPageNum()).append("' onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\" /> 页");  
	    builder.append("</span>");  
	    builder.append(" <a onclick=\"_toPageGO(").append(page.getPages()).append(")");  
	    builder.append("\" class=\"pageGO\">跳转</a>  ");  
	    builder.append("</div>");  
	    if (null != bodyContentVal && !"".equals(bodyContentVal)) {  
	        builder.append(bodyContentVal);  
	    }  
	    builder.append("</span>");  
	    builder.append("</div>");  
        try {  
        	/**
        	 * 分页样式一样，不同的类型调用不同的跳转分页函数
        	 */
        	if(StringUtils.isNotBlank(ajaxType) && "true".equals(ajaxType)){
        		builder.append(ajaxPage());
        	}else{
        		builder.append(commonPage());
        	}
        	pageContext.getOut().print(builder.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.error("分页标签错误",e);  
        }  
        return EVAL_PAGE;  
    }  
	
	/**
	 * 异步ajax的分页查询条件，无form添加
	 * @param bodyContentVal
	 * @return
	 */
	private String ajaxPage()
	{
		StringBuilder builder = new StringBuilder();  
	    //分页 提交 函数  
	    builder.append("<script type=\"text/javascript\">");
	    builder.append("function _toPage(pageNum) {");    
	    builder.append("if(!isNaN(pageNum)){");  
	    builder.append("pageNum=pageNum;");  
	    builder.append("}else{pageNum=1;};");  
	    builder.append("var hiddenInput = document.getElementById('_pageNum_');");
        builder.append("if(hiddenInput == null || hiddenInput == 'undefined') {hiddenInput = document.createElement('input');}");
        builder.append("hiddenInput.type='hidden'; hiddenInput.id='_pageNum_'; hiddenInput.name='pageNum'; hiddenInput.value=pageNum;");
	    builder.append("var queryForm = document.getElementById('").append(formId).append("');");
	    builder.append("queryForm.appendChild(hiddenInput);");
	    builder.append("commonQuery();");//调用查询方法
	    builder.append("}");    
	    builder.append("function _toPageGO(total) {");    
	    builder.append("var pageNum= document.getElementById('_pageNum').value; " );  
	    builder.append("if(!isNaN(total)){");  
	    builder.append("pageNum=pageNum > total ? total : pageNum;");  
	    builder.append("pageNum=pageNum<=0 ? 1 :pageNum;" );  
	    builder.append("}else{pageNum=1;}");  
	    builder.append("var hiddenInput = document.getElementById('_pageNum_');");
        builder.append("if(hiddenInput == null || hiddenInput == 'undefined') {hiddenInput = document.createElement('input');}");
	    builder.append("hiddenInput.type='hidden'; hiddenInput.name='pageNum'; hiddenInput.value=pageNum;");
	    builder.append("var queryForm = document.getElementById('").append(formId).append("');");
	    builder.append("queryForm.appendChild(hiddenInput);");
	    builder.append("commonQuery();");//调用查询方法
	    builder.append("}");    
	    builder.append("</script>");  
	    return builder.toString();
	}
	
	/**
	 * 通用分页 form提交
	 * @param bodyContentVal
	 * @return
	 */
	private String commonPage()
	{
        StringBuilder builder = new StringBuilder();  
        //分页 提交 函数  
        builder.append("<script type=\"text/javascript\">");
        builder.append("function _toPage(pageNum) {");    
        builder.append("if(!isNaN(pageNum)){");  
        builder.append("pageNum=pageNum;");  
        builder.append("}else{pageNum=1;};");  
        builder.append("var hiddenInput = document.createElement('input');");
        builder.append("hiddenInput.type='hidden'; hiddenInput.name='pageNum'; hiddenInput.value=pageNum;");
        builder.append("var queryForm = document.getElementById('").append(formId).append("');");
        builder.append("queryForm.appendChild(hiddenInput);");
        builder.append("$('#").append(formId).append("').submit();");    
        builder.append("}");    
        builder.append("function _toPageGO(total) {");    
        builder.append("var pageNum= document.getElementById('_pageNum').value; " );  
        builder.append("if(!isNaN(total)){");  
        builder.append("pageNum=pageNum > total ? total : pageNum;");  
        builder.append("pageNum=pageNum<=0 ? 1 :pageNum;" );  
        builder.append("}else{pageNum=1;}");  
        builder.append("var hiddenInput = document.createElement('input');");
        builder.append("hiddenInput.type='hidden'; hiddenInput.name='pageNum'; hiddenInput.value=pageNum;");
        builder.append("var queryForm = document.getElementById('").append(formId).append("');");
        builder.append("queryForm.appendChild(hiddenInput);");
        builder.append("$('#").append(formId).append("').submit();");       
        builder.append("}");    
        builder.append("</script>");  
        return builder.toString();
	}
  
    public String getFormId()
	{
		return formId;
	}

	public void setFormId(String formId)
	{
		this.formId = formId;
	}

	public Page<E> getPage() {  
        return page;  
    }  
  
    public void setPage(Page<E> page) {  
        this.page = page;  
    }

	public String getAjaxType() {
		return ajaxType;
	}

	public void setAjaxType(String ajaxType) {
		this.ajaxType = ajaxType;
	}

}
