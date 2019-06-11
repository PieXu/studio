package com.innovate.user.user.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.annotation.LikeQuery;
import com.innovate.basic.base.BaseModel;

public class UserGroup extends BaseModel{
	@Invisible
	public static final String TAB_NAME = "T_SYS_USER_GROUP";
	@Invisible
	private static final long serialVersionUID = -1265994234005874376L;
	@LikeQuery
	private String name;
	@LikeQuery
	private String code;
	private String delFlag;
	
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
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
