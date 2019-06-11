package com.innovate.basic.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
* <p>Title: CommonQueryFiled</p>
* <p>Description: 查询域封装输出</p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月17日
 */
public class CommonQueryFiled extends BodyTagSupport {

	private static final long serialVersionUID = -4411704660369460342L;
	//页面传入属性参数
	private List<PageSet> list;
	private Map<String,Object> conditions;
	
	private DicUtil dicUtil =DicFactory.getDicUtil();

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * 封装输出 
	 * <option value="text">文本</option> 
	 * <option value="date">日期</option>
	 * <option value="dic">字典</option>
	 * <option value="array">数组</option>
	 * <option value="tag">标签</option>
	 */
	@Override
	public int doEndTag() throws JspException {
		StringBuffer sbuffer = new StringBuffer();
		try {
			for(PageSet set : list)
			{
				if("text".equalsIgnoreCase(set.getDataType())){ //普通文本输出
					sbuffer.append("<span style=\"vertical-align: bottom;padding-right:20px\">")
						   .append(set.getShowName()).append("：")
						   .append("<span style=\"vertical-align: bottom;padding-right:15px\"><input type=\"text\" id=\"")
						   .append(set.getAttributeName())
						   .append("\" name=\"")
						   .append(set.getAttributeName())
						   .append("\" class=\"input-text datetimepicker-input radius\" style=\"width:150px;\" placeholder=\"请输入")
						   .append(set.getShowName())
						   .append("\" value=\"");
						   if(conditions.containsKey(set.getAttributeName())){
							   sbuffer.append(conditions.get(set.getAttributeName()));
						   }
				   sbuffer.append("\"/></span>");
				}else if("date".equalsIgnoreCase(set.getDataType())){//日期时间输出
					sbuffer.append("<span style=\"vertical-align: bottom;padding-right:20px\">")
					       .append(set.getShowName()).append("：")
					       .append("<input type=\"text\" id=\"")
						   .append(set.getAttributeName()+"Start")
						   .append("\" name=\"")
						   .append(set.getAttributeName()+"Start")
						   .append("\" value=\"");
					if(conditions.containsKey(set.getAttributeName()+"Start")){
						  sbuffer.append(conditions.get(set.getAttributeName()+"Start"));
					}
					sbuffer.append("\" onfocus=\"WdatePicker({maxDate:'#F{$dp.$D(\\\'")
						   .append(set.getAttributeName()+"End")
						   .append("\\\')||\\\'%y-%M-%d\\\'}'})\" class=\"input-text Wdate radius\" style=\"width:120px;\"/>&nbsp;至&nbsp;")
						   .append("<input type=\"text\" id=\"")
						   .append(set.getAttributeName()+"End")
						   .append("\" name=\"")
						   .append(set.getAttributeName()+"End")
						   .append("\" value=\"");
					if(conditions.containsKey(set.getAttributeName()+"End")){
						  sbuffer.append(conditions.get(set.getAttributeName()+"End"));
					}
					sbuffer.append("\" onfocus=\"WdatePicker({minDate:'#F{$dp.$D(\\\'")
						   .append(set.getAttributeName()+"Start")
						   .append("\\\')}',maxDate:'%y-%M-%d'})\" class=\"input-text Wdate radius\" style=\"width:120px;\"/></span>");
				}else if("dic".equalsIgnoreCase(set.getDataType())){
					List<Dic> dicList = dicUtil.getDicList(set.getDataBody());
					sbuffer.append("<span style=\"vertical-align: bottom;padding-right:20px\">")
					 	   .append(set.getShowName()).append("：")	
					 	   .append("<span class=\"select-box inline radius\">")
					 	   .append("<select name=\"")
						   .append(set.getAttributeName())
						   .append("\" id=\"")
						   .append(set.getAttributeName())
						   .append("\" class=\"select radius\" size=\"1\">")
						   .append("<option value=\"\">请选择...</option>");
					if(!dicList.isEmpty()){
						for(Dic dic : dicList)
						{
							sbuffer.append("<option value=\""); 
							sbuffer.append(dic.getId());
							sbuffer.append("\" ");  
					        if(conditions.containsKey(set.getAttributeName())){
					        	sbuffer.append(" selected"); 
					        }
					        sbuffer.append(" >"); 
					        sbuffer.append(dic.getName());  
					        sbuffer.append(" </option>");  
						}
					}
				   sbuffer.append("</select></span></span>");
				}else if("json".equalsIgnoreCase(set.getDataType())){// 自定义json数据组
					@SuppressWarnings("unchecked")
					Map<String,Object> jsonMap = (Map<String,Object>)JSON.parse(set.getDataBody());
					sbuffer.append("<span style=\"vertical-align: bottom;padding-right:20px\">")
				 	   .append(set.getShowName()).append("：")	
				 	   .append("<span class=\"select-box inline radius\">")
				 	   .append("<select name=\"")
					   .append(set.getAttributeName())
					   .append("\" id=\"")
					   .append(set.getAttributeName())
					   .append("\" class=\"select radius\" size=\"1\">")
					   .append("<option value=\"\">请选择...</option>");
					if(!jsonMap.isEmpty()){
						for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
							sbuffer.append("<option value=\""); 
							sbuffer.append(entry.getKey());
							sbuffer.append("\" ");  
					        if(conditions.containsKey(set.getAttributeName())){
					        	sbuffer.append(" selected"); 
					        }
					        sbuffer.append(" >"); 
					        sbuffer.append(entry.getValue());  
					        sbuffer.append(" </option>"); 
						}
					}
					sbuffer.append("</select></span></span>");
				}else if("tag".equalsIgnoreCase(set.getDataType())){//自定义TAG调用
					sbuffer.append(set.getDataBody());
				}
			}
			pageContext.getOut().print(sbuffer);
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtils.error(CommonQueryFiled.class,"CommonQueryFiled 列表查询域标签错误，请检查参数是否正确", e);
		}
		return EVAL_PAGE;
	}
	
	public List<PageSet> getList() {
		return list;
	}

	public void setList(List<PageSet> list) {
		this.list = list;
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}

}
