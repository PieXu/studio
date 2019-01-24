package com.innovate.user.role.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * 角色列表
 * @author  
 *
 */
public class Role extends BaseModel {
	@Invisible
	public static final String TAB_NAME = "T_SYS_ROLE";
	@Invisible
	private static final long serialVersionUID = -8803695882954993337L;
	
	private String name;
	private String code;
	private String type;
	private String status;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
