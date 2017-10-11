package com.yidusoft.project.business.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "visiting_record")
public class VisitingRecord {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 记录编号
     */
    @Column(name = "record_code")
    private String recordCode;

    /**
     * 来访目的
     */
    @Column(name = "Visiting_purpose")
    private String visitingPurpose;

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
     * 是否删除 0 否 1是
     */
    private Integer deleted;

    /**
     * 来访对象
     */
    @Column(name = "visitor_id")
    private String visitorId;

    /**
     * 过程记录
     */
    @Column(name = "process_log")
    private String processLog;

    /**
     * 后续计划
     */
    @Column(name = "Follow_up_plan")
    private String followUpPlan;

    /**
     * 备注
     */
    private String remarks;

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
     * 获取记录编号
     *
     * @return record_code - 记录编号
     */
    public String getRecordCode() {
        return recordCode;
    }

    /**
     * 设置记录编号
     *
     * @param recordCode 记录编号
     */
    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    /**
     * 获取来访目的
     *
     * @return Visiting_purpose - 来访目的
     */
    public String getVisitingPurpose() {
        return visitingPurpose;
    }

    /**
     * 设置来访目的
     *
     * @param visitingPurpose 来访目的
     */
    public void setVisitingPurpose(String visitingPurpose) {
        this.visitingPurpose = visitingPurpose;
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
     * 获取是否删除 0 否 1是
     *
     * @return deleted - 是否删除 0 否 1是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除 0 否 1是
     *
     * @param deleted 是否删除 0 否 1是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取来访对象
     *
     * @return visitor_id - 来访对象
     */
    public String getVisitorId() {
        return visitorId;
    }

    /**
     * 设置来访对象
     *
     * @param visitorId 来访对象
     */
    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    /**
     * 获取过程记录
     *
     * @return process_log - 过程记录
     */
    public String getProcessLog() {
        return processLog;
    }

    /**
     * 设置过程记录
     *
     * @param processLog 过程记录
     */
    public void setProcessLog(String processLog) {
        this.processLog = processLog;
    }

    /**
     * 获取后续计划
     *
     * @return Follow_up_plan - 后续计划
     */
    public String getFollowUpPlan() {
        return followUpPlan;
    }

    /**
     * 设置后续计划
     *
     * @param followUpPlan 后续计划
     */
    public void setFollowUpPlan(String followUpPlan) {
        this.followUpPlan = followUpPlan;
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