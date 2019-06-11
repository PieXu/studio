package com.innovate.basic.tag;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.alibaba.fastjson.JSON;
import com.innovate.plat.model.PageSet;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.dic.service.impl.DicFactory;
import com.innovate.util.LoggerUtils;

/**
 * 
 * <p>
 * Title: CommonQueryFiled
 * </p>
 * <p>
 * Description: 编辑新增页面输出
 * </p>
 * <p>
 * Company: easysoft.ltd
 * </p>
 * 
 * @author IvanHsu
 * @date 2019年5月17日
 */
public class CommonEditFiled extends BodyTagSupport {

	private static final long serialVersionUID = 2149663674365314403L;
	// 页面传入属性参数
	private List<PageSet> list;
	private LinkedHashMap<String, Object> bizObject;
	private DicUtil dicUtil = DicFactory.getDicUtil();

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * 封装输出 
	 * <option value="text">文本</option> 
	 * <option value="date">日期</option>
	 * <option value="dic">字典</option> 
	 * <option value="json">数组</option>
	 * <option value="tag">标签</option>
	 */
	@Override
	public int doEndTag() throws JspException {
		StringBuffer sbuffer = new StringBuffer();
		try {
			if (!list.isEmpty()) {
				for(PageSet set : list) {
					if("show".equalsIgnoreCase(set.getIsEdit())){
						sbuffer.append("<div class=\"row cl\"><label class=\"form-label col-xs-2 col-sm-2\">")
							   .append(set.getShowName())//<span class="c-red">*</span> 名称标红的设置
							   .append("</label><div class=\"formControls col-xs-10 col-sm-10\">");
						if ("date".equalsIgnoreCase(set.getDataType())) {
							sbuffer.append("<input type=\"text\"  class=\"input-text Wdate radius\" style=\"width:120px;\" value=\"")
							   .append(null!=bizObject?bizObject.get(set.getAttributeName()):"")
							   .append("\" onfocus=\"WdatePicker()\" ")
						       .append(" id=\"")
						       .append(set.getAttributeName())
						       .append("\" name=\"")
						       .append(set.getAttributeName())
						       .append("\" />");
						} else if ("dic".equalsIgnoreCase(set.getDataType())) {
							List<Dic> dicList = dicUtil.getDicList(set.getDataBody().toString());
							sbuffer.append("<span class=\"select-box\">")
								   .append("<select name=\"")
								   .append(set.getAttributeName())
								   .append("\" class=\"select radius\" size=1>")
								   .append("<option value=\"\">请选择...</option>");
							for(Dic dic : dicList){
								sbuffer.append("<option  value=\"")
									   .append(dic.getId())
									   .append("\" ");
								//设置选中
								if(null!=bizObject && dic.getId().equals(bizObject.get(set.getAttributeName()))){
									sbuffer.append(" selected "); 
								}
								sbuffer.append(" >").append(dic.getName()).append("</option>");
							}
							sbuffer.append("</select></span> ");
						} else if ("json".equalsIgnoreCase(set.getDataType())) {
							@SuppressWarnings("unchecked")
							Map<String, Object> jsonMap = (Map<String, Object>) JSON.parse(set.getDataBody());
							sbuffer.append("<span class=\"select-box\">")
							   .append("<select name=\"")
							   .append(set.getAttributeName())
							   .append("\" class=\"select radius\" size=1>")
							   .append("<option value=\"\">请选择...</option>");
							for(Entry<String, Object> entry : jsonMap.entrySet()){
								sbuffer.append("<option  value=\"")
									   .append(entry.getValue())
									   .append("\" ");
								//设置选中
								if(null!=bizObject && entry.getValue().equals(bizObject.get(set.getAttributeName()))){
									sbuffer.append(" selected "); 
								}
								sbuffer.append(" >").append(entry.getKey()).append("</option>");
							}
							sbuffer.append("</select></span> ");
						} else if ("text".equalsIgnoreCase(set.getDataType())) {
							sbuffer.append("<input type=\"text\" class=\"input-text radius\" value=\"")
								   .append(null!=bizObject?bizObject.get(set.getAttributeName()):"")
							       .append("\" id=\"")
							       .append(set.getAttributeName())
							       .append("\" name=\"")
							       .append(set.getAttributeName())
							       .append("\" />");
						}
						sbuffer.append("</div></div>");
					}else if("hidden".equalsIgnoreCase(set.getIsEdit())){//设置隐藏域
						sbuffer.append("<input type=\"hidden\" name=\"")
							   .append(set.getAttributeName())
							   .append("\" value=\"")
							   .append(null!=bizObject?bizObject.get(set.getAttributeName()):"")
							   .append("\"/>");
					}
				}
			}
			pageContext.getOut().print(sbuffer);
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtils.error(CommonEditFiled.class, "CommonEditFiled 编辑域标签错误，请检查参数是否正确", e);
		}
		return EVAL_PAGE;
	}

	public List<PageSet> getList() {
		return list;
	}

	public void setList(List<PageSet> list) {
		this.list = list;
	}

	public LinkedHashMap<String, Object> getBizObject() {
		return bizObject;
	}

	public void setBizObject(LinkedHashMap<String, Object> bizObject) {
		this.bizObject = bizObject;
	}

}
