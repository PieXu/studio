package com.innovate.sys.dic.model;

import com.innovate.basic.base.BaseModel;

/**
 * @desc:数据字典
 * @time: 2017年6月20日 下午4:02:17
 * @author IvanHsu
 */
public class Dic extends BaseModel {

	private static final long serialVersionUID = -8089299549993345416L;

	private String name;
	private String code;
	private String categoryCode;
	private DicCategory category;
	private String status;
	private Integer seq;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getCategoryCode()
	{
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode)
	{
		this.categoryCode = categoryCode;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Integer getSeq()
	{
		return seq;
	}

	public void setSeq(Integer seq)
	{
		this.seq = seq;
	}

	public DicCategory getCategory()
	{
		return category;
	}

	public void setCategory(DicCategory category)
	{
		this.category = category;
	}

}
