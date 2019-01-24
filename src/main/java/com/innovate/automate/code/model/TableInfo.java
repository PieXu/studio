package com.innovate.automate.code.model;

import java.io.Serializable;

/**
 * 表定义实体
 * @author IvanHsu
 * @2018年4月16日 下午12:53:04
 */
public class TableInfo implements Serializable{

	private static final long serialVersionUID = -7015157061940985356L;
	private String tableCat;
	private String tableSchem;
	private String tableName;
	private String remarks;
	private String tableType;
	public String getTableCat() {
		return tableCat;
	}
	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}
	public String getTableSchem() {
		return tableSchem;
	}
	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
}
