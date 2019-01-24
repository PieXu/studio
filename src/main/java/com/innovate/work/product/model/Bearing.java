package com.innovate.work.product.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * 业务对象 轴承的基本信息
 */
public class Bearing extends BaseModel {
	@Invisible
	private static final long serialVersionUID = -3131681617594238603L;

	private String productName;// 名称
	private String description;// 摘要描述
	private String type;// 型号
	private String category;// 分类
	private String brand;// 品牌
	private String outDiameter;// 外径
	private String inDiameter;// 内径
	private String thickness;// 厚度
	private String coverImage;// 封面图片
	private String standard;//规格详细描述
	private int isHot;//默认不是

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOutDiameter() {
		return outDiameter;
	}

	public void setOutDiameter(String outDiameter) {
		this.outDiameter = outDiameter;
	}

	public String getInDiameter() {
		return inDiameter;
	}

	public void setInDiameter(String inDiameter) {
		this.inDiameter = inDiameter;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

}
