package com.innovate.basic.tag;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.innovate.plat.model.PageSet;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.dic.service.impl.DicFactory;
import com.innovate.util.DateUtils;
import com.innovate.util.LoggerUtils;
import com.innovate.util.SessionUtils;

/**
 * 
* <p>Title: CommonQueryFiled</p>
* <p>Description: 显示列表封装输出</p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月17日
 */
public class CommonListFiled extends BodyTagSupport {

	private static final long serialVersionUID = -5747391200517424899L;
	//页面传入属性参数
	private List<PageSet> list;
	private DicUtil dicUtil =DicFactory.getDicUtil() ;
	private Page<Map<String,Object>> page;;

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
		StringBuffer sbuffer = new StringBuffer("<table class=\"table table-border table-bordered table-bg table-hover table-sort\">");
		try {
			Dic yesDic = dicUtil.getDicY();
			//取证参数分析
			Pattern p=Pattern.compile("\\{(\\w+)\\}");
			StringBuffer header = new StringBuffer();
			StringBuffer body = new StringBuffer();
			header.append("<thead><tr class=\"text-l\">")
				  .append("<th width=\"35\" class=\"text-c\">序号</th>");
			for(PageSet set : list)
			{
				//Table Header
				if(null != set.getIsList() && "show".equalsIgnoreCase(set.getIsList())){
					header.append("<th width=\"")
					 	  .append(set.getWidth())
						  .append("\">")
						  .append(set.getShowName())
						  .append("</th>");
				}
				//Table Body
			}
			List<Map<String,Object>> optList = getCurrentResLinkOptList();
			if(!optList.isEmpty())
			{
				header.append("<th class=\"text-c\" width=\"")
			 	  .append(optList.size()*25)
				  .append("\">操作</th>");
			}
			header.append("</tr></thead>");
			List<Map<String,Object>> result = page.getResult();
			if(!result.isEmpty())
			{
				body.append("<tbody>");
				int index= 1;
				for(Map<String,Object> trMap : result)
				{
					body.append("<tr>").append("<td class=\"text-c\">").append(index).append("</td>");
					for(PageSet set : list)
					{
						if(null != set.getIsList() && "show".equalsIgnoreCase(set.getIsList())){
							body.append("<td>");
							if("date".equalsIgnoreCase(set.getDataType())){
								if(StringUtils.isNotBlank(set.getDataBody())){
									body.append(DateUtils.getCustomDateString((Date)trMap.get(set.getAttributeName()), set.getDataBody()));
								}else{
									body.append(DateUtils.getCustomDateString((Date)trMap.get(set.getAttributeName()), "yyyy-MM-dd HH:mm:ss"));
								}
							}else if("dic".equalsIgnoreCase(set.getDataType())){
								Dic dic = dicUtil.getDicInfo(trMap.get(set.getAttributeName()).toString());
								if(null!=dic){
									body.append(dic.getName());
								}
							}else if("json".equalsIgnoreCase(set.getDataType())){
								@SuppressWarnings("unchecked")
								Map<String,Object> jsonMap = (Map<String,Object>)JSON.parse(set.getDataBody());
				    	        if(null!=jsonMap){
				    	        	body.append(jsonMap.get(trMap.get(set.getAttributeName()).toString()));
				    	        }
							}else if("text".equalsIgnoreCase(set.getDataType())){
								body.append(trMap.get(set.getAttributeName()));
							}
							body.append("</td>");
						}
					}
					//页面列表配置的操作输出
					if(!optList.isEmpty())
					{
						body.append("<td class=\"f-16 td-manage text-c\">");
						for(Map<String,Object> optMap : optList)
						{
							String url = optMap.get("url").toString();
							Matcher matcher=p.matcher(url);
							url = url.substring(0, url.indexOf("?")+1);
							int k = 0;
							while(matcher.find()){
						    	String paranname = matcher.group(1);
						    	if(k > 0){
						    		url+="&";
			    				}
						    	url+=paranname+"="+trMap.get(paranname);
						    }
							body.append("<a style=\"text-decoration:none\" class=\"ml-5\" data-toggle=\"tooltip\" data-placement=\"bottom\"  href=\"javascript:void(0);\" title=\"")
								.append(optMap.get("name"))
								.append("\" onclick='");
							if(null!=optMap.get("isWindow") && yesDic.getId().equals(optMap.get("isWindow"))){
								body.append("_common_open_win(")
								    .append(optMap.get("width")).append(",")
									.append(optMap.get("height")).append(",\"")
									.append(url).append("\");");
							}else{
								body.append("_deals_Ajax(")
									.append("\"").append(url)
									.append("\",\"").append(optMap.get("name"))
									.append("\");");
							}
							body.append("'><i class=\"Hui-iconfont ")
								.append(optMap.get("iconFont"))
								.append("\"></i></a>");
						}
						body.append("</td>");
					}
					body.append("</tr>");
					index++;
				}
				body.append("</tbody>");
			}
			sbuffer.append(header).append(body).append("</table>");
			pageContext.getOut().print(sbuffer);
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtils.error(CommonListFiled.class,"CommonQueryFiled 列表查询域标签错误，请检查参数是否正确", e);
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 当前菜单配置的操作
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private  List<Map<String,Object>> getCurrentResLinkOptList()
	{
		Dic buttonOpt = dicUtil.getDicInfo("SYS_OPT_TYPE", "OPT_TYPE_LINK");
	    Map<String,List<Map<String,Object>>> sessionAttribute = (Map<String, List<Map<String,Object>>>) SessionUtils.getSessionAttribute("_menuOptMap");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if(null!=sessionAttribute){
			 if(null!= sessionAttribute && !sessionAttribute.isEmpty()){
				 String currentMenu = (String) session.getAttribute("_currentMenu");
				 List<Map<String, Object>> resultRes = new LinkedList<Map<String, Object>>();
				 List<Map<String, Object>> linkRes = sessionAttribute.get(currentMenu);
				 for (Iterator<Map<String, Object>> iterator = linkRes.iterator(); iterator.hasNext();) {
					Map<String, Object> map = iterator.next();
					if (null != map.get("type") && buttonOpt.getId().equals(map.get("type"))) {
						resultRes.add(map);
					}
				 }
				 return resultRes;
			 }
		}
		return null;
	}
	
	public List<PageSet> getList() {
		return list;
	}

	public void setList(List<PageSet> list) {
		this.list = list;
	}

	public Page<Map<String, Object>> getPage() {
		return page;
	}

	public void setPage(Page<Map<String, Object>> page) {
		this.page = page;
	}

}
