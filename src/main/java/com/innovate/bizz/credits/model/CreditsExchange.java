package com.innovate.bizz.credits.model;

import java.util.Date;

import com.innovate.basic.annotation.Invisible;

public class CreditsExchange {
	
	@Invisible
	public static final String TAB_NAME = "jiujiu_credits_exchange";
	
    //主键
    private String id;

    //用户编号
    private String userId;
    private String userName;

    //换购商品编号
    private String productId;
    private String productName;

    //换购时间
    private Date exchangeTime;

    //换购状态
    private String status;

    //换购积分
    private Double exchangeScore;

    //备注说明
    private String comments;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

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
     * 获取用户编号
     *
     * @return USER_ID - 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取换购商品编号
     *
     * @return PRODUCT_ID - 换购商品编号
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置换购商品编号
     *
     * @param productId 换购商品编号
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取换购时间
     *
     * @return EXCHANGE_TIME - 换购时间
     */
    public Date getExchangeTime() {
        return exchangeTime;
    }

    /**
     * 设置换购时间
     *
     * @param exchangeTime 换购时间
     */
    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    /**
     * 获取换购状态
     *
     * @return STATUS - 换购状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置换购状态
     *
     * @param status 换购状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取换购积分
     *
     * @return EXCHANGE_SCORE - 换购积分
     */
    public Double getExchangeScore() {
        return exchangeScore;
    }

    /**
     * 设置换购积分
     *
     * @param exchangeScore 换购积分
     */
    public void setExchangeScore(Double exchangeScore) {
        this.exchangeScore = exchangeScore;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}