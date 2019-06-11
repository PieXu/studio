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
	private String isWindow; // 是否是窗口
	private String type;// 类型， 按钮，链接
	private String url; // 调用的地址链接
	private Double width;// 宽度
	private Double height;// 高度
	private String title;// 窗口标题
	private String iconFont;// 图标样式

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconFont() {
		return iconFont;
	}

	public void setIconFont(String iconFont) {
		this.iconFont = iconFont;
	}

	public String getIsWindow() {
		return isWindow;
	}

	public void setIsWindow(String isWindow) {
		this.isWindow = isWindow;
	}

}
