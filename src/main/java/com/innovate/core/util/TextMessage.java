package com.innovate.core.util;

import java.io.Serializable;

/**
 * 返回消息
 * 
 * @author IvanHsu
 * @2018年4月11日 下午5:30:48
 */
public class TextMessage implements Serializable {

	private static final long serialVersionUID = -5055377725329635487L;

	private String title;
	
	private String content;
	
	public TextMessage() {
		super();
	}

	public TextMessage(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
