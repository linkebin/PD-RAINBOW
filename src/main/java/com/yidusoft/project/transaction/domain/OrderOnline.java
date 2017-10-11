package com.yidusoft.project.transaction.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_online")
public class OrderOnline {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 问卷购买数量
     */
    @Column(name = "questionnaire_total")
    private Integer questionnaireTotal;

    /**
     * 应付金额
     */
    @Column(name = "order_money")
    private BigDecimal orderMoney;

    /**
     * 支付方式
     */
    @Column(name = "payment_method")
    private String paymentMethod;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付人
     */
    private String creator;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 支付状态0 已支付 1 未支付
     */
    @Column(name = "order_state")
    private Integer orderState;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 所属产品
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * @return ID_
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_code - 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编号
     *
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取问卷购买数量
     *
     * @return questionnaire_total - 问卷购买数量
     */
    public Integer getQuestionnaireTotal() {
        return questionnaireTotal;
    }

    /**
     * 设置问卷购买数量
     *
     * @param questionnaireTotal 问卷购买数量
     */
    public void setQuestionnaireTotal(Integer questionnaireTotal) {
        this.questionnaireTotal = questionnaireTotal;
    }

    /**
     * 获取应付金额
     *
     * @return order_money - 应付金额
     */
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    /**
     * 设置应付金额
     *
     * @param orderMoney 应付金额
     */
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * 获取支付方式
     *
     * @return payment_method - 支付方式
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 设置支付方式
     *
     * @param paymentMethod 支付方式
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取支付人
     *
     * @return creator - 支付人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置支付人
     *
     * @param creator 支付人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取是否删除
     *
     * @return deleted - 是否删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除
     *
     * @param deleted 是否删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取支付状态0 已支付 1 未支付
     *
     * @return order_state - 支付状态0 已支付 1 未支付
     */
    public Integer getOrderState() {
        return orderState;
    }

    /**
     * 设置支付状态0 已支付 1 未支付
     *
     * @param orderState 支付状态0 已支付 1 未支付
     */
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取支付时间
     *
     * @return payment_time - 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置支付时间
     *
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取所属产品
     *
     * @return product_id - 所属产品
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置所属产品
     *
     * @param productId 所属产品
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
}