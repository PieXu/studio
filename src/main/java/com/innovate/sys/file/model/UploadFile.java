/**
 * @name:UploadFile.java
 * @package:com.xu.sys.model
 * @time: 2017年7月10日 上午9:44:23
 * @author IvanHsu 
 */
package com.innovate.sys.file.model;

import com.innovate.basic.base.BaseModel;

/**
 * @desc:上传文件保存处理
 * @package:com.xu.sys.model
 * @time: 2017年7月10日 上午9:44:23
 * @author IvanHsu
 */
public class UploadFile extends BaseModel {

	private static final long serialVersionUID = -6632711057914708685L;

	private String name;
	private String type;
	private String ext;
	private long size;
	private String objectId;
	private String isTemp;
	private String path;
	private String createUser;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
