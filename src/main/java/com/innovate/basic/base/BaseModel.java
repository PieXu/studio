/**
 * @name:BaseModel.java
 * @time: 2017年6月12日 下午4:11:45
 * @author IvanHsu 
 */
package com.innovate.basic.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @time: 2017年6月12日 下午4:11:45
 * @author
 */
@SuppressWarnings("serial")
public class BaseModel implements Serializable {

	private String id;

	private String comments;

	private Date updateTime;

	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
