package com.innovate.bizz.goods.model;

import java.util.Date;

import com.innovate.basic.annotation.Invisible;

public class Goods {
	
	@Invisible
	public static final String TAB_NAME = "jiujiu_goods_info";
	
    //主键
    private String id;

    //产品名称
    private String goodsName;

    //产品类型
    private String goodsType;

    //单价
    private Double price;

    //总量
    private Integer amount;

    //购买限制（扩展）
    private Integer goodsLimits;

    //产品描述
    private String goodsSummary;

    //备注
    private String comments;

    //创建时间
    private Date createTime;

    //更细时间
    private Date updateTime;

    //操作人
    private String operator;

    //产品标签
    private String goodsTag;

    //产品封面
    private String coverImage;

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
     * 获取产品名称
     *
     * @return GOODS_NAME - 产品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置产品名称
     *
     * @param goodsName 产品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取产品类型
     *
     * @return GOODS_TYPE - 产品类型
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置产品类型
     *
     * @param goodsType 产品类型
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取单价
     *
     * @return PRICE - 单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取总量
     *
     * @return AMOUNT - 总量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置总量
     *
     * @param amount 总量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取购买限制（扩展）
     *
     * @return GOODS_LIMITS - 购买限制（扩展）
     */
    public Integer getGoodsLimits() {
        return goodsLimits;
    }

    /**
     * 设置购买限制（扩展）
     *
     * @param goodsLimits 购买限制（扩展）
     */
    public void setGoodsLimits(Integer goodsLimits) {
        this.goodsLimits = goodsLimits;
    }

    /**
     * 获取产品描述
     *
     * @return GOODS_SUMMARY - 产品描述
     */
    public String getGoodsSummary() {
        return goodsSummary;
    }

    /**
     * 设置产品描述
     *
     * @param goodsSummary 产品描述
     */
    public void setGoodsSummary(String goodsSummary) {
        this.goodsSummary = goodsSummary;
    }

    /**
     * 获取备注
     *
     * @return COMMENTS - 备注
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置备注
     *
     * @param comments 备注
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
     * 获取更细时间
     *
     * @return UPDATE_TIME - 更细时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更细时间
     *
     * @param updateTime 更细时间
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
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取产品标签
     *
     * @return GOODS_TAG - 产品标签
     */
    public String getGoodsTag() {
        return goodsTag;
    }

    /**
     * 设置产品标签
     *
     * @param goodsTag 产品标签
     */
    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

}