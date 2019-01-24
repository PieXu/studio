package com.innovate.user.user.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * @author IvanHsu
 * @2018年3月25日 下午9:50:50
 */
public class UserRole extends BaseModel {

	@Invisible
	private static final long serialVersionUID = 4564135012205193718L;
	@Invisible
	public static final String TAB_NAME = "t_user_role";

	private String roleId;
	private String userId;
	private String status;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
