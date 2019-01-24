package com.innovate.sys.resource.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

public class ResOpt extends BaseModel {
	@Invisible
	public static final String TAB_NAME = "T_RES_OPT";
	@Invisible
	private static final long serialVersionUID = 3799789201270474982L;

	private String resId;
	private String optId;

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
