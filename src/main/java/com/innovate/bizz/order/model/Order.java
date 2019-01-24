package com.innovate.bizz.order.model;

import java.util.Date;

import com.innovate.basic.annotation.Invisible;

public class Order {
	
	@Invisible
	public static final String TAB_NAME = "jiujiu_order_info";
	
    //主键
    private String id;

    //用户ID
    private String userId;
    //用户姓名
    private String userName;

    //产品编号
    private String goodsId;
    //产品名称
    private String goodsName;

    //原价价格
    private Double orginPrice;

    //购买价格
    private Double price;

    //购买数量
    private Integer amount;

    //购买总价
    private Double totalPrice;

    //订单状态
    private String orderStatus;

    //下单时间
    private Date orderTime;

    //支付方式
    private String payWay;

    //支付时间
    private Date payTime;

    //备注
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
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取产品编号
     *
     * @return GOODS_ID - 产品编号
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置产品编号
     *
     * @param goodsId 产品编号
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取原价价格
     *
     * @return ORGIN_PRICE - 原价价格
     */
    public Double getOrginPrice() {
        return orginPrice;
    }

    /**
     * 设置原价价格
     *
     * @param orginPrice 原价价格
     */
    public void setOrginPrice(Double orginPrice) {
        this.orginPrice = orginPrice;
    }

    /**
     * 获取购买价格
     *
     * @return PRICE - 购买价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置购买价格
     *
     * @param price 购买价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取购买数量
     *
     * @return AMOUNT - 购买数量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置购买数量
     *
     * @param amount 购买数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取购买总价
     *
     * @return TOTAL_PRICE - 购买总价
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置购买总价
     *
     * @param totalPrice 购买总价
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取订单状态
     *
     * @return ORDER_STATUS - 订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     *
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取下单时间
     *
     * @return ORDER_TIME - 下单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 设置下单时间
     *
     * @param orderTime 下单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取支付方式
     *
     * @return PAY_WAY - 支付方式
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * 设置支付方式
     *
     * @param payWay 支付方式
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    /**
     * 获取支付时间
     *
     * @return PAY_TIME - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
}