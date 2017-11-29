package com.yidusoft.project.transaction.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "product_settings")
public class ProductSettings {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 产品编号
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 问卷数量
     */
    @Column(name = "product_total")
    private Integer productTotal;

    /**
     * 问卷套餐价格
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除0 否 1删除
     */
    private Integer deleted;

    /**
     * 营销ID
     */
    @Column(name = "promotions_id")
    private String promotionsId;

    /**
     * 营销名称
     */
    @Column(name = "promotions_name")
    private String promotionsName;
    /**
     * 套餐名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 启用状态
     */
    @Column(name = "product_state")
    private Integer productState;

    /**
     * 套餐类型
     */
    @Column(name = "product_type")
    private Integer productType;

    /**
     * 时间期限
     */
    @Column(name = "time_limit")
    private Integer timeLimit;


    /**
     * 套餐的销售数量
     */
    @javax.persistence.Transient
    private Integer count;

    @javax.persistence.Transient
    private Date paymentTime;

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
     * 获取产品编号
     *
     * @return product_code - 产品编号
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编号
     *
     * @param productCode 产品编号
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取问卷数量
     *
     * @return product_total - 问卷数量
     */
    public Integer getProductTotal() {
        return productTotal;
    }

    /**
     * 设置问卷数量
     *
     * @param productTotal 问卷数量
     */
    public void setProductTotal(Integer productTotal) {
        this.productTotal = productTotal;
    }

    /**
     * 获取问卷套餐价格
     *
     * @return product_price - 问卷套餐价格
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 设置问卷套餐价格
     *
     * @param productPrice 问卷套餐价格
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
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
     * 获取是否删除0 否 1删除
     *
     * @return deleted - 是否删除0 否 1删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除0 否 1删除
     *
     * @param deleted 是否删除0 否 1删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取营销ID
     *
     * @return promotions_id - 营销ID
     */
    public String getPromotionsId() {
        return promotionsId;
    }

    /**
     * 设置营销ID
     *
     * @param promotionsId 营销ID
     */
    public void setPromotionsId(String promotionsId) {
        this.promotionsId = promotionsId;
    }

    /**
     * 获取套餐名称
     * @return
     */
    public String getProductName() { return productName; }

    /**
     * 设置套餐名称
     * @param productName
     */
    public void setProductName(String productName) { this.productName = productName; }

    /**
     * 获取营销ID
     *
     * @return promotions_id - 营销ID
     */
    public String getPromotionsName() {
        return promotionsName;
    }

    /**
     * 设置营销ID
     *
     * @param promotionsName 营销ID
     */
    public void setPromotionsName(String promotionsName) {
        this.promotionsName = promotionsName;
    }

    /**
     * 获取套餐状态
     * @return
     */
    public Integer getProductState() {
        return productState;
    }

    /**
     * 设置套餐状态
     * @param productState
     */
    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    /**
     * 获取套餐类型
     * @return
     */
    public Integer getProductType() {
        return productType;
    }

    /**
     * 设置套餐类型
     * @param productType
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    /**
     * 获取时间期限
     * @return
     */
    public Integer getTimeLimit() {
        return timeLimit;
    }

    /**
     * 设置时间期限
     * @param timeLimit
     */
    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * 获取销售数量
     * @return
     */
    public Integer getCount() { return count; }

    /**
     * 设置销售数量
     * @param count
     */
    public void setCount(Integer count) { this.count = count; }

    /**
     * 获取支付时间
     * @return
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置支付时间
     * @param paymentTime
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }
}