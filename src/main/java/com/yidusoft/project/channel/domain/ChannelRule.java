package com.yidusoft.project.channel.domain;

import javax.persistence.*;

@Table(name = "channel_rule")
public class ChannelRule {
    /**
     * 渠道商ID
     */
    @Column(name = "channel_id")
    private String channelId;

    /**
     * 规则ID
     */
    @Column(name = "rule_id")
    private String ruleId;

    /**
     * 获取渠道商ID
     *
     * @return channel_id - 渠道商ID
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置渠道商ID
     *
     * @param channelId 渠道商ID
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取规则ID
     *
     * @return rule_id - 规则ID
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置规则ID
     *
     * @param ruleId 规则ID
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}