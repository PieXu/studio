package com.innovate.basic.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.innovate.user.user.model.User;
import com.innovate.util.CommonCons;
import com.innovate.util.LoggerUtils;

public class ShowOptTag extends BodyTagSupport {

	private static final long serialVersionUID = -5192292929454791177L;
	private static final String  TYPE_BUTTON ="button";
	private static final String  TYPE_LINK ="link";
	private static final String  DEFAULT_BUTTON_CLASS ="btn btn-primary radius";
	private static final String  DEFAULT_LINK_CLASS ="ml-5";
	
	private String code;
	private String method;
	private String styleClass;
	private String title;
	private String type;
	private String iconfont;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
			 if(CommonCons.Y_N_ENUM.Y.toString().equals(loginUser.getIsSuperUser())){
				 if(StringUtils.isNotBlank(type) && TYPE_BUTTON.equalsIgnoreCase(type)){
					 pageContext.getOut().print(outButtonString());
				 }else if(StringUtils.isNotBlank(type) && TYPE_LINK.equalsIgnoreCase(type)){
					 pageContext.getOut().print(outLinkString());
				 }
	         }else{
				if(StringUtils.isNotBlank(code)){
					if(checkPremisson(code)){
						if(StringUtils.isNotBlank(type) && TYPE_BUTTON.equalsIgnoreCase(type)){
							 pageContext.getOut().print(outButtonString());
						 }else if(StringUtils.isNotBlank(type) && TYPE_LINK.equalsIgnoreCase(type)){
							 pageContext.getOut().print(outLinkString());
						 }
					}
				}
	         }
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtils.fmtError(CheckTag.class, e, "ShowOptTag标签初始化错误，请检查参数是否正确", e);
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 输出内容 button
	 * @return
	 */
	private String outButtonString(){
		StringBuilder builder = new StringBuilder();
		builder.append("<input type=\"button\"")
		   .append("value=\"").append(title).append("\"")
		   .append("title=\"").append(title).append("\"");
		if(StringUtils.isNotBlank(styleClass)){
			builder.append(" class=\"").append(styleClass).append("\"");
		}else{
			builder.append(" class=\"").append(DEFAULT_BUTTON_CLASS).append("\"");
		}
		if(StringUtils.isNotBlank(method)){
			builder.append(" onclick=\"").append(method).append("\"");
		}
		builder.append(" />");
		return builder.toString();
	}

	/**
	 * 输出内容 link
	 * @return
	 */
	private String outLinkString(){
		StringBuilder builder = new StringBuilder();
		builder.append("<a style=\"text-decoration:none\"   data-toggle=\"tooltip\" data-placement=\"bottom\" class=\"ml-5\" href=\"javascript:;\" ")
		   .append("value=\"").append(title).append("\"")
		   .append("title=\"").append(title).append("\"");
		if(StringUtils.isNotBlank(styleClass)){
			builder.append(" class=\"").append(styleClass).append("\"");
		}else{
			builder.append(" class=\"").append(DEFAULT_LINK_CLASS).append("\"");
		}
		if(StringUtils.isNotBlank(method)){
			builder.append(" onclick=\"").append(method).append("\"");
		}
		builder.append(">");
		if(StringUtils.isNotBlank(iconfont)){
			builder.append("<i class=\"Hui-iconfont\">").append(iconfont).append("</i>");
		}else{
			builder.append(title);
		}
		builder.append("</a>");
		return builder.toString();
	}
	/**
	 * 判断是否有操作权限
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkPremisson(String code) 
	{
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if(null!=session){
			 Map<String,List<String>> menuOptMap  = (Map<String, List<String>>) session.getAttribute("_menuOptMap");
			 if(null!= menuOptMap && !menuOptMap.isEmpty()){
				 String currentMenu = (String) session.getAttribute("_currentMenu");
				 List<String> strList = menuOptMap.get(currentMenu);
				 if(null!= strList && !strList.isEmpty()){
					 return strList.contains(code);
				 }
			 }
		}
		return false;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIconfont() {
		return iconfont;
	}

	public void setIconfont(String iconfont) {
		this.iconfont = iconfont;
	}
}
