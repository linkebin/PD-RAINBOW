package com.yidusoft.project.channel.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "clearing_manage")
public class ClearingManage {
    @Id
    @Column(name = "clear_id")
    private String clearId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "rule_id")
    private String ruleId;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 结算佣金
     */
    private BigDecimal brokerage;

    /**
     * 是否清算款项：1未清算，2以清算
     */
    private Integer status;

    /**
     * @return clear_id
     */
    public String getClearId() {
        return clearId;
    }

    /**
     * @param clearId
     */
    public void setClearId(String clearId) {
        this.clearId = clearId;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return channel_id
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * @return rule_id
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * @param ruleId
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取结算佣金
     *
     * @return brokerage - 结算佣金
     */
    public BigDecimal getBrokerage() {
        return brokerage;
    }

    /**
     * 设置结算佣金
     *
     * @param brokerage 结算佣金
     */
    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    /**
     * 获取是否清算款项：1未清算，2以清算
     *
     * @return status - 是否清算款项：1未清算，2以清算
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否清算款项：1未清算，2以清算
     *
     * @param status 是否清算款项：1未清算，2以清算
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}