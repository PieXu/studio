package com.innovate.bizz.credits.model;

import java.util.Date;

import com.innovate.basic.annotation.Invisible;

public class CreditsProduct {
	
	@Invisible
	public static final String TAB_NAME = "jiujiu_credits_products";
	
    //主键
    private String id;

    //换购商品
    private String produceName;

    //需要积分
    private Double creditsScore;
    
    private Integer amount;

    //商品图片
    private String productImage;

    //商品状态
    private String status;

    //备注说明
    private String comments;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
    
    private String operator;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取换购商品
     *
     * @return PRODUCE_NAME - 换购商品
     */
    public String getProduceName() {
        return produceName;
    }

    /**
     * 设置换购商品
     *
     * @param produceName 换购商品
     */
    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
     * 获取需要积分
     *
     * @return CREDITS_SCORE - 需要积分
     */
    public Double getCreditsScore() {
        return creditsScore;
    }

    /**
     * 设置需要积分
     *
     * @param creditsScore 需要积分
     */
    public void setCreditsScore(Double creditsScore) {
        this.creditsScore = creditsScore;
    }

    /**
     * 获取商品图片
     *
     * @return PRODUCT_IMAGE - 商品图片
     */
    public String getProductImage() {
        return productImage;
    }

    /**
     * 设置商品图片
     *
     * @param productImage 商品图片
     */
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    /**
     * 获取商品状态
     *
     * @return STATUS - 商品状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置商品状态
     *
     * @param status 商品状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取备注说明
     *
     * @return COMMENTS - 备注说明
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置备注说明
     *
     * @param comments 备注说明
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
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
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
    
}