package com.innovate.bizz.message.model;

import java.util.Date;

import com.innovate.basic.annotation.Invisible;

public class Message {
	
	@Invisible
	public static final String TAB_NAME = "jiujiu_notice_info";
	// 主键
	private String id;

	private String noticeType;

	// 标题
	private String title;

	// 标签
	private String flag;

	// 创建时间
	private Date createTime;

	// 更新时间
	private Date updateTime;

	// 操作人
	private String operator;

	// 内容
	private String content;

	/**
	 * 获取主键
	 *
	 * @return ID - 主键
	 */
	public String getId() {
		return id;
	}


	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}



	/**
	 * 设置主键
	 *
	 * @param id
	 *            主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取标题
	 * @return TITLE - 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取标签
	 * @return FLAG - 标签
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 设置标签
	 * @param flag
	 * 标签
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 获取创建时间
	 * @return CREATE_TIME - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return UPDATE_TIME - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取操作人
	 *
	 * @return OPERATOR - 操作人
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置操作人
	 *
	 * @param operator
	 *            操作人
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取内容
	 *
	 * @return CONTENT - 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 *
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
