package com.innovate.bizz.shop.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * 
 * <p>
 * Title: Shop
 * </p>
 * <p>
 * Description: 商铺实体
 * </p>
 * <p>
 * Company: easysoft.ltd
 * </p>
 * 
 * @author IvanHsu
 * @date 2019年5月15日
 */
public class Shop extends BaseModel {

	private static final long serialVersionUID = -7969633631896607247L;
	@Invisible
	public static final String TAB_NAME = "biz_shop_info";
	private String shopName;
	private String operator;// 创建人
	private String accessKey;// 访问秘钥
	private String accessState;
	private String delFlag;
	private String coverImage;// 店铺封面

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessState() {
		return accessState;
	}

	public void setAccessState(String accessState) {
		this.accessState = accessState;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

}
