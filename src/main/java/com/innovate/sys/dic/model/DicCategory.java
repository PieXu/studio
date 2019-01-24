package com.innovate.sys.dic.model;

import com.innovate.basic.base.BaseModel;

/**
 * @desc:数据字典分类
 * @time: 2017年7月5日 下午5:48:57
 * @author IvanHsu
 */
public class DicCategory extends BaseModel {

	private static final long serialVersionUID = -6741957072046914102L;
	private String categoryName;
	private String categoryCode;
	private String categoryType;
	private String status;

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public String getCategoryCode()
	{
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode)
	{
		this.categoryCode = categoryCode;
	}

	public String getCategoryType()
	{
		return categoryType;
	}

	public void setCategoryType(String categoryType)
	{
		this.categoryType = categoryType;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}
