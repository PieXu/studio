package com.innovate.plat.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * <p>
 * Title: PageSet
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: easysoft.ltd
 * </p>
 * 
 * @author IvanHsu
 * @date 2019年5月16日
 */
public class PageSet extends BaseModel {

	@Invisible
	private static final long serialVersionUID = -7472810649416475468L;
	@Invisible
	public static final String TAB_NAME = "t_page_set";

	private String columnName;
	private String attributeName;
	private String showName;
	private String filedType;
	private String width;
	private String isQuery;
	private String dataType;
	private String dataBody;
	private String pageId;
	private String seq;
	private String isList;
	private String isEdit;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getFiledType() {
		return filedType;
	}

	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataBody() {
		return dataBody;
	}

	public void setDataBody(String dataBody) {
		this.dataBody = dataBody;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

}
