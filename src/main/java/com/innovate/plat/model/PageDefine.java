package com.innovate.plat.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.annotation.LikeQuery;
import com.innovate.basic.base.BaseModel;

/**
 * 
 * <p>
 * Title: PageDefine
 * </p>
 * <p>
 * Description: 页面定义
 * </p>
 * <p>
 * Company: easysoft.ltd
 * </p>
 * 
 * @author IvanHsu
 * @date 2019年5月15日
 */
public class PageDefine extends BaseModel {

	@Invisible
	private static final long serialVersionUID = -8901316656440783778L;
	@Invisible
	public static final String TAB_NAME = "t_page_def";

	private String pageCode;
	private String pageName;
	private String operator;
	private String delFlag;
	@LikeQuery
	private String tableName;
	private String sqlBody;
	private String sqlWhere;

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSqlBody() {
		return sqlBody;
	}

	public void setSqlBody(String sqlBody) {
		this.sqlBody = sqlBody;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

}
