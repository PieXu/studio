package com.innovate.plat.model;

import java.util.Map;

/**
 * 
 * <p>
 * Title: RequestProcess
 * </p>
 * <p>
 * Description: 处理请求的查询SQL
 * </p>
 * <p>
 * Company: easysoft.ltd
 * </p>
 * 
 * @author IvanHsu
 * @date 2019年5月17日
 */
public class RequestProcess {

	private Integer pageSize;
	private Integer pageNum;
	private String orderBy;
	private Map<String, Object> params;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
