package com.yidusoft.project.transaction.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "questionnaire_promotions")
public class QuestionnairePromotions {
    /**
     * 一个套餐一个活动
     */
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 促销类型 1 时间段套餐折扣 2买送
     */
    @Column(name = "promotions_type")
    private Integer promotionsType;

    /**
     * 开始时间
     */
    @Column(name = "promotions_start")
    private Date promotionsStart;

    /**
     * 促销结束时间
     */
    @Column(name = "promotions_end")
    private Date promotionsEnd;

    /**
     * 属于1的  折扣
     */
    @Column(name = "promotions_discount")
    private BigDecimal promotionsDiscount;

    /**
     * 设置购买次数
     */
    @Column(name = "buy_total")
    private Integer buyTotal;

    /**
     * 属于2的  买送多少问卷
     */
    @Column(name = "promotions_buy_send")
    private Integer promotionsBuySend;

    /**
     * 创建人
     */
    @Column(name = "creator")
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 活动是否开启 0  是 1 否
     */
    @Column(name = "promotions_state")
    private Integer promotionsState;

    /**
     * 是否删除
     */
    @Column(name = "deleted")
    private Integer deleted;

    /**
     * 活动名称
     */
    @Column(name = "promotions_name")
    private String promotionsName;

    @Transient
    private ProductSettings productSettings;

    /**
     * 获取一个套餐一个活动
     *
     * @return ID_ - 一个套餐一个活动
     */
    public String getId() {
        return id;
    }

    /**
     * 设置一个套餐一个活动
     *
     * @param id 一个套餐一个活动
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取促销类型 1 时间段套餐折扣 2买送
     *
     * @return promotions_type - 促销类型 1 时间段套餐折扣 2买送
     */
    public Integer getPromotionsType() {
        return promotionsType;
    }

    /**
     * 设置促销类型 1 时间段套餐折扣 2买送
     *
     * @param promotionsType 促销类型 1 时间段套餐折扣 2买送
     */
    public void setPromotionsType(Integer promotionsType) {
        this.promotionsType = promotionsType;
    }

    /**
     * 获取开始时间
     *
     * @return promotions_start - 开始时间
     */
    public Date getPromotionsStart() {
        return promotionsStart;
    }

    /**
     * 设置开始时间
     *
     * @param promotionsStart 开始时间
     */
    public void setPromotionsStart(Date promotionsStart) {
        this.promotionsStart = promotionsStart;
    }

    /**
     * 获取促销结束时间
     *
     * @return promotions_end - 促销结束时间
     */
    public Date getPromotionsEnd() {
        return promotionsEnd;
    }

    /**
     * 设置促销结束时间
     *
     * @param promotionsEnd 促销结束时间
     */
    public void setPromotionsEnd(Date promotionsEnd) {
        this.promotionsEnd = promotionsEnd;
    }

    /**
     * 获取属于1的  折扣
     *
     * @return promotions_discount - 属于1的  折扣
     */
    public BigDecimal getPromotionsDiscount() {
        return promotionsDiscount;
    }

    /**
     * 设置属于1的  折扣
     *
     * @param promotionsDiscount 属于1的  折扣
     */
    public void setPromotionsDiscount(BigDecimal promotionsDiscount) {
        this.promotionsDiscount = promotionsDiscount;
    }

    /**
     * 获取设置购买次数
     *
     * @return buy_total - 设置购买次数
     */
    public Integer getBuyTotal() {
        return buyTotal;
    }

    /**
     * 设置设置购买次数
     *
     * @param buyTotal 设置购买次数
     */
    public void setBuyTotal(Integer buyTotal) {
        this.buyTotal = buyTotal;
    }

    /**
     * 获取属于2的  买送多少问卷
     *
     * @return promotions_buy_send - 属于2的  买送多少问卷
     */
    public Integer getPromotionsBuySend() {
        return promotionsBuySend;
    }

    /**
     * 设置属于2的  买送多少问卷
     *
     * @param promotionsBuySend 属于2的  买送多少问卷
     */
    public void setPromotionsBuySend(Integer promotionsBuySend) {
        this.promotionsBuySend = promotionsBuySend;
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
     * 获取活动是否开启 0  是 1 否
     *
     * @return promotions_state - 活动是否开启 0  是 1 否
     */
    public Integer getPromotionsState() {
        return promotionsState;
    }

    /**
     * 设置活动是否开启 0  是 1 否
     *
     * @param promotionsState 活动是否开启 0  是 1 否
     */
    public void setPromotionsState(Integer promotionsState) {
        this.promotionsState = promotionsState;
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
     * 获取活动名称
     * @return
     */
    public String getPromotionsName() { return promotionsName; }

    /**
     * 设置活动名称
     * @param promotionsName
     */
    public void setPromotionsName(String promotionsName) { this.promotionsName = promotionsName; }

    public ProductSettings getProductSettings() {
        return productSettings;
    }

    public void setProductSettings(ProductSettings productSettings) {
        this.productSettings = productSettings;
    }

}