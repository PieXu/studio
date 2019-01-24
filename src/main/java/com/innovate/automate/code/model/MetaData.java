package com.innovate.automate.code.model;

import java.io.Serializable;

/**
 * 数据库表元数据
 * @author IvanHsu
 * @2018年4月16日 上午11:11:13
 */
public class MetaData implements Serializable {

	private static final long serialVersionUID = 21435226993889766L;
	// 数据库列名
	private String metaColumnKey;
	// 属性名
	private String metaColumnName;
	// 列注释
	private String metaColumnCommnet;

	public String getMetaColumnKey() {
		return metaColumnKey;
	}

	public void setMetaColumnKey(String metaColumnKey) {
		this.metaColumnKey = metaColumnKey;
	}

	public String getMetaColumnName() {
		return metaColumnName;
	}

	public void setMetaColumnName(String metaColumnName) {
		this.metaColumnName = metaColumnName;
	}

	public String getMetaColumnCommnet() {
		return metaColumnCommnet;
	}

	public void setMetaColumnCommnet(String metaColumnCommnet) {
		this.metaColumnCommnet = metaColumnCommnet;
	}

}
