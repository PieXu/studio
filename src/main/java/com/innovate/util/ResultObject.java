package com.innovate.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象
 */
public class ResultObject {
	private String result;
	private String message;
	private Map<String,Object> data = new HashMap<String,Object>();

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}


	/**
	 * 
	 *操作的结果类型
	 */
	public static enum OPERATE_RESULT{
		success,
		fail,
		empty;
		private OPERATE_RESULT(){}
	}
	
}
