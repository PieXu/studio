package com.innovate.sys.resource.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * 操作信息表
 * 
 * @author IvanHsu
 * @2018年3月26日 上午10:54:30
 */
public class Opt extends BaseModel {

	@Invisible
	public static final String TAB_NAME = "T_SYS_OPT";
	@Invisible
	private static final long serialVersionUID = 7447963750749633241L;

	private String name;
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
