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

import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.dic.service.impl.DicFactory;
import com.innovate.user.user.model.User;
import com.innovate.util.CommonCons;
import com.innovate.util.LoggerUtils;
import com.innovate.util.SessionUtils;

public class OptButtonTag extends BodyTagSupport {

	private static final long serialVersionUID = 4705488191377147514L;
	
	private DicUtil dicUtil = DicFactory.getDicUtil();
	
	private static final String  TYPE_BUTTON ="button";
	private static final String  TYPE_LINK ="link";
	private static final String  DEFAULT_BUTTON_CLASS ="btn btn-primary radius";
	private static final String  DEFAULT_LINK_CLASS ="ml-5";
	
	private String styleClass;
	private String type;
	private String iconfont;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * 输出
	 */
	@Override
	public int doEndTag() throws JspException {
		try {
			if (StringUtils.isBlank(type) || TYPE_BUTTON.equalsIgnoreCase(type)) {
				pageContext.getOut().print(outButtonString());
			} else if (StringUtils.isNotBlank(type) && TYPE_LINK.equalsIgnoreCase(type)) {
				pageContext.getOut().print(outLinkString());
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
		List<Map<String,Object>> currentResOptList = getCurrentResOptList();
		Dic buttonOpt = dicUtil.getDicInfo("SYS_OPT_TYPE", "OPT_TYPE_BUTTON");
		if(null!=currentResOptList){
			for(Map<String,Object> map : currentResOptList){
				if(buttonOpt.getId().equals(map.get("type"))){ // 按钮域的输出通过 类型控制一下
					builder.append("<input type=\"button\"")
					   .append("value=\"").append(map.get("name")).append("\"")
					   .append("id=\"").append(map.get("type")).append("_opt_id\"")
					   .append("title=\"").append(map.get("type")).append("\"");
					if(StringUtils.isNotBlank(styleClass)){
						builder.append(" class=\"").append(styleClass).append("\"");
					}else{
						builder.append(" class=\"").append(DEFAULT_BUTTON_CLASS).append("\"");
					}
					// 弹出窗口的操作方法
					if(map.containsKey("isWindow") && map.get("isWindow").equals(dicUtil.getDicY().getId())){
						builder.append(" onclick='_common_open_win(")
							   .append(map.get("width")).append(",")
							   .append(map.get("height")).append(",\"")
							   .append(map.get("url")).append("\");'");
					}else{//异步链接的方法
						builder.append(" onclick='_deals_Ajax(");
						builder.append("\"").append(map.get("url"));
						builder.append("\",\"").append(map.get("name"));
						builder.append("\");'");
					}
					builder.append("/>&nbsp;");
				}
			}
		}
		return builder.toString();
	}

	/**
	 * 输出内容 link
	 * @return
	 */
	private String outLinkString(){
		StringBuilder builder = new StringBuilder();
		User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
		// 超级管理员的权限的输出按钮的操作
		if(CommonCons.Y_N_ENUM.Y.toString().equals(loginUser.getIsSuperUser())){
			List<Map<String,Object>> currentResOptList = getCurrentResOptList();
			if(null!=currentResOptList){
				for(Map<String,Object> map : currentResOptList){
					builder.append("<a style=\"text-decoration:none\" class=\"ml-5\" href=\"javascript:;\" ")
					   .append("value=\"").append(map.get("name")).append("\"_opt_id");
					if(StringUtils.isNotBlank(styleClass)){
						builder.append(" class=\"").append(styleClass).append("\"");
					}else{
						builder.append(" class=\"").append(DEFAULT_LINK_CLASS).append("\"");
					}
					builder.append(">");
					if(StringUtils.isNotBlank(iconfont)){
						builder.append("<i class=\"Hui-iconfont\">").append(iconfont).append("</i>");
					}else{
						builder.append(map.get("name"));
					}
					builder.append("</a>");
				}
			}
		}else{ // 角色用户的操作按钮
		}
		return builder.toString();
	}
//	/**
//	 * 判断是否有操作权限
//	 * @param code
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	private boolean checkPremisson(String code) 
//	{
//		Subject currentUser = SecurityUtils.getSubject();
//		Session session = currentUser.getSession();
//		if(null!=session){
//			 Map<String,List<Opt>> menuOptMap  = (Map<String, List<Opt>>) session.getAttribute("_menuOptMap");
//			 if(null!= menuOptMap && !menuOptMap.isEmpty()){
//				 String currentMenu = (String) session.getAttribute("_currentMenu");
//				 List<Opt> strList = menuOptMap.get(currentMenu);
//				 if(null!= strList && !strList.isEmpty()){
//					 return strList.contains(code);
//				 }
//			 }
//		}
//		return false;
//	}
	
	/**
	 * 当前菜单配置的操作
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private  List<Map<String,Object>> getCurrentResOptList()
	{
	    Map<String,List<Map<String,Object>>> sessionAttribute = (Map<String, List<Map<String,Object>>>) SessionUtils.getSessionAttribute("_menuOptMap");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if(null!=sessionAttribute){
			 if(null!= sessionAttribute && !sessionAttribute.isEmpty()){
				 String currentMenu = (String) session.getAttribute("_currentMenu");
				 return sessionAttribute.get(currentMenu);
			 }
		}
		return null;
	}


	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
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
