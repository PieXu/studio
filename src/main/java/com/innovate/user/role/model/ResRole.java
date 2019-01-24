package com.innovate.user.role.model;

import java.io.Serializable;

import com.innovate.basic.annotation.Invisible;

public class ResRole implements Serializable {

	@Invisible
	public static final String TAB_NAME = "t_role_res";
	@Invisible
	private static final long serialVersionUID = 856824490470194366L;
	private String id;
	private String resId;
	private String roleId;
	
	public ResRole() {
		super();
	}
	public ResRole(String id, String resId, String roleId) {
		super();
		this.id = id;
		this.resId = resId;
		this.roleId = roleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
