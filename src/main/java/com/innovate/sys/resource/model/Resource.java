package com.innovate.sys.resource.model;

import java.util.List;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * 菜单对象
 */
public class Resource extends BaseModel {
	
	@Invisible
	public static final String TABLE_NAME = "t_sys_resource";
	@Invisible
	private static final long serialVersionUID = 7056983755257679481L;

	private String resName;
	private String parent;
	private String link;
	private String code;
	private String classStyle;
	private Integer seqNum;
	private String menuType;
	private String resOpt;
	@Invisible
	private String path;
	
	@Invisible
	private List<Resource> childRes;

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public List<Resource> getChildRes() {
		return childRes;
	}

	public void setChildRes(List<Resource> childRes) {
		this.childRes = childRes;
	}

	public String getResOpt() {
		return resOpt;
	}

	public void setResOpt(String resOpt) {
		this.resOpt = resOpt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
