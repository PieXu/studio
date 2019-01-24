package com.innovate.automate.code.model;

import java.io.Serializable;

/**
 * 数据库表字段
 * @author IvanHsu
 * @2018年4月16日 上午11:12:19
 */
public class ColumnMap implements Serializable 
{
	private static final long serialVersionUID = 4717622732850120217L;
	// 数据库列名
	private String dataColumnName;
	// 属性名
	private String columnName;
	// 列注释
	private String columnCommnet;
	// 首字母大写
	private String firstColumnName;
	// 数据库类型
	private Integer dataBaseType;
	// java类型
	private String javaType;
	// java定义类型
	private String javaDefType;
	// 是否主键
	private String primaryKey;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(Integer dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getFirstColumnName() {
		return firstColumnName;
	}

	public void setFirstColumnName(String firstColumnName) {
		this.firstColumnName = firstColumnName;
	}

	public String getDataColumnName() {
		return dataColumnName;
	}

	public void setDataColumnName(String dataColumnName) {
		this.dataColumnName = dataColumnName;
	}

	public String getJavaDefType() {
		return javaDefType;
	}

	public void setJavaDefType(String javaDefType) {
		this.javaDefType = javaDefType;
	}

	public String getColumnCommnet() {
		return columnCommnet;
	}

	public void setColumnCommnet(String columnCommnet) {
		this.columnCommnet = columnCommnet;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

}
