package com.innovate.automate.code.model;

/**
 * 数据库表字段
 * 
 * @author IvanHsu
 * @2018年4月16日 上午11:12:19
 */
public class ColumnInfo {
	// 对应属性名 驼峰样式
	private String attributeName;
	// 数据库列名
	private String columnName;
	// 列注释
	private String columnCommnet;
	// 数据库类型
	private Integer dbType;
	// 类型名称
	private String typeName;
	// 长度大小
	private String size;
	//对应的表
	private String tabelName;
	//表别名，用于数据sql生成多个表区分
	private String tableAlias;

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnCommnet() {
		return columnCommnet;
	}

	public void setColumnCommnet(String columnCommnet) {
		this.columnCommnet = columnCommnet;
	}

	public Integer getDbType() {
		return dbType;
	}

	public void setDbType(Integer dbType) {
		this.dbType = dbType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTabelName() {
		return tabelName;
	}

	public void setTabelName(String tabelName) {
		this.tabelName = tabelName;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

}
