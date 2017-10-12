package com.yidusoft.project.transaction.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

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
}