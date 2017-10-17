package com.yidusoft.project.channel.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "account_rule")
public class AccountRule {
    @Id
    @Column(name = "rule_id")
    private String ruleId;

    /**
     * 规则比例
     */
    private String rule;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 规则名称
     */
    @Column(name = "rule_name")
    private String ruleName;

    /**
     * 规则开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 规则结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 标记为默认规则（只能修改，不能删除和添加）
     */
    @Column(name = "default_rule")
    private Integer defaultRule;

    @Column(name = "deleted")
    private Integer deleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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
     * 获取规则比例
     *
     * @return rule - 规则比例
     */
    public String getRule() {
        return rule;
    }

    /**
     * 设置规则比例
     *
     * @param rule 规则比例
     */
    public void setRule(String rule) {
        this.rule = rule;
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
     * 获取规则名称
     *
     * @return rule_name - 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置规则名称
     *
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 获取规则开始时间
     *
     * @return start_time - 规则开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置规则开始时间
     *
     * @param startTime 规则开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取规则结束时间
     *
     * @return end_time - 规则结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置规则结束时间
     *
     * @param endTime 规则结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取标记为默认规则（只能修改，不能删除和添加）
     *
     * @return default_rule - 标记为默认规则（只能修改，不能删除和添加）
     */
    public Integer getDefaultRule() {
        return defaultRule;
    }

    /**
     * 设置标记为默认规则（只能修改，不能删除和添加）
     *
     * @param defaultRule 标记为默认规则（只能修改，不能删除和添加）
     */
    public void setDefaultRule(Integer defaultRule) {
        this.defaultRule = defaultRule;
    }
}