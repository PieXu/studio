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
	private String width;
	private String height;
	private String url;

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

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
