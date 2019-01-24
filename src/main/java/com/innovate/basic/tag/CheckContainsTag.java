package com.innovate.basic.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.innovate.util.LoggerUtils;

/**
 * @desc:自定义标签
 * @time: 2017年6月21日 上午9:29:28
 * @author IvanHsu
 */
public class CheckContainsTag extends BodyTagSupport {

	private static final long serialVersionUID = -2177054316975552588L;

	private List<?> list;
	private String value;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		String check = "";
		if (null!=list &&! list.isEmpty()) {
			if(list.contains(value)){
				check = "checked=\"checked\"";
			}
		}
		try {
			pageContext.getOut().print(check);
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtils.error(CheckContainsTag.class,"checkbox标签初始化错误，请检查参数是否正确", e);
		}
		return EVAL_PAGE;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
