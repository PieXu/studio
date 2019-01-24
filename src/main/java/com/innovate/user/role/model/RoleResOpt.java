package com.innovate.user.role.model;

import java.io.Serializable;

import com.innovate.basic.annotation.Invisible;

/**
 * 角色菜单操作信息
 * @author IvanHsu
 * @2018年3月30日 下午3:39:21
 */
public class RoleResOpt implements Serializable{

	@Invisible
	public static final String TAB_NAME = "t_role_res_opt";
	@Invisible
	private static final long serialVersionUID = -8369496527473844036L;
	
	private String roleId;
	private String resId;
	private String optId;
	
	public RoleResOpt() {
		super();
	}
	public RoleResOpt(String roleId, String resId, String optId) {
		super();
		this.roleId = roleId;
		this.resId = resId;
		this.optId = optId;
	}
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getOptId() {
		return optId;
	}
	public void setOptId(String optId) {
		this.optId = optId;
	}

}
