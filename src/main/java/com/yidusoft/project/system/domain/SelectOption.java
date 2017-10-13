package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "select_option")
public class SelectOption {
    @Id
    @Column(name = "option_id")
    private String optionId;

    @Column(name = "option_name")
    private String optionName;

    @Column(name = "option_value")
    private String optionValue;

    private String creator;

    /**
     * 选项类别：包括维修，SSHY：所属行业，BJLX：报价类型等
     */
    @Column(name = "option_category")
    private String optionCategory;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除标志 0:未删除 ，1:已删除
     */
    private Integer deleted;

    /**
     * 备注
     */
    private String remarks;

    /**
     * @return option_id
     */
    public String getOptionId() {
        return optionId;
    }

    /**
     * @param optionId
     */
    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    /**
     * @return option_name
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * @param optionName
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    /**
     * @return option_value
     */
    public String getOptionValue() {
        return optionValue;
    }

    /**
     * @param optionValue
     */
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
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
     * 获取选项类别：包括维修，SSHY：所属行业，BJLX：报价类型等
     *
     * @return option_category - 选项类别：包括维修，SSHY：所属行业，BJLX：报价类型等
     */
    public String getOptionCategory() {
        return optionCategory;
    }

    /**
     * 设置选项类别：包括维修，SSHY：所属行业，BJLX：报价类型等
     *
     * @param optionCategory 选项类别：包括维修，SSHY：所属行业，BJLX：报价类型等
     */
    public void setOptionCategory(String optionCategory) {
        this.optionCategory = optionCategory;
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
     * 获取是否删除标志 0:未删除 ，1:已删除
     *
     * @return deleted - 是否删除标志 0:未删除 ，1:已删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除标志 0:未删除 ，1:已删除
     *
     * @param deleted 是否删除标志 0:未删除 ，1:已删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}