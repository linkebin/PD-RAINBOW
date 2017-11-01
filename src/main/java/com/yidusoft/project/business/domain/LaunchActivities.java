package com.yidusoft.project.business.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "launch_activities")
public class LaunchActivities {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 活动编号
     */
    @Column(name = "activity_code")
    private String activityCode;

    /**
     * 活动名称
     */
    @Column(name = "activity_name")
    private String activityName;

    /**
     * 活动目的
     */
    @Column(name = "activity_aim")
    private String activityAim;

    /**
     * 参与方
     */
    private String participant;

    /**
     * 活动地址
     */
    @Column(name = "activity_addr")
    private String activityAddr;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 活动人数
     */
    @Column(name = "activity_total")
    private Integer activityTotal;

    /**
     * 问卷id
     */
    @Column(name = "uestionnaire_id")
    private String uestionnaireId;

    /**
     * 问卷名称
     */
    @Column(name = "uestionnaire_name")
    private String uestionnaireName;

    /**
     * 活动开始时间
     */
    @Column(name = "activity_start")
    private Date activityStart;

    /**
     * 活动结束时间
     */
    @Column(name = "activity_end")
    private Date activityEnd;

    /**
     * 问卷访问网址
     */
    @Column(name = "uestionnaire_uri")
    private String uestionnaireUri;

    /**
     * 活动邀请码
     */
    @Column(name = "activity_porn")
    private String activityPorn;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除 0 否  1 是
     */
    private Integer deleted;

    /**
     * 发起人类型 0咨询师   1平台调研员(需要审批才会生产链接与邀请码)
     */
    @Column(name = "Initiator_type")
    private Integer initiatorType;

    /**
     * 活动状态(供需审批的类型使用)
     */
    @Column(name = "activity_status")
    private Integer activityStatus;

    /**
     * 创建人
     */
    @Column(name = "creator")
    private String creator;

    /**
     * 创建人Id
     */
    @Column(name = "user_id")
    private String userId;

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
     * 获取活动编号
     *
     * @return activity_code - 活动编号
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * 设置活动编号
     *
     * @param activityCode 活动编号
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    /**
     * 获取活动名称
     *
     * @return activity_name - 活动名称
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 设置活动名称
     *
     * @param activityName 活动名称
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * 获取活动目的
     *
     * @return activity_aim - 活动目的
     */
    public String getActivityAim() {
        return activityAim;
    }

    /**
     * 设置活动目的
     *
     * @param activityAim 活动目的
     */
    public void setActivityAim(String activityAim) {
        this.activityAim = activityAim;
    }

    /**
     * 获取参与方
     *
     * @return participant - 参与方
     */
    public String getParticipant() {
        return participant;
    }

    /**
     * 设置参与方
     *
     * @param participant 参与方
     */
    public void setParticipant(String participant) {
        this.participant = participant;
    }

    /**
     * 获取活动地址
     *
     * @return activity_adress - 活动地址
     */
    public String getActivityAddr() {
        return activityAddr;
    }

    /**
     * 设置活动地址
     *
     * @param activityAddr 活动地址
     */
    public void setActivityAddr(String activityAddr) {
        this.activityAddr = activityAddr;
    }

    /**
     * 获取联系人
     *
     * @return contacts - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取活动人数
     *
     * @return activity_total - 活动人数
     */
    public Integer getActivityTotal() {
        return activityTotal;
    }

    /**
     * 设置活动人数
     *
     * @param activityTotal 活动人数
     */
    public void setActivityTotal(Integer activityTotal) {
        this.activityTotal = activityTotal;
    }

    /**
     * 获取问卷id
     *
     * @return uestionnaire_id - 问卷id
     */
    public String getUestionnaireId() {
        return uestionnaireId;
    }

    /**
     * 设置问卷id
     *
     * @param uestionnaireId 问卷id
     */
    public void setUestionnaireId(String uestionnaireId) {
        this.uestionnaireId = uestionnaireId;
    }

    /**
     * 获取问卷名称
     *
     * @return uestionnaire_name - 问卷名称
     */
    public String getUestionnaireName() {
        return uestionnaireName;
    }

    /**
     * 设置问卷名称
     *
     * @param uestionnaireName 问卷名称
     */
    public void setUestionnaireName(String uestionnaireName) {
        this.uestionnaireName = uestionnaireName;
    }

    /**
     * 获取活动开始时间
     *
     * @return activity_start - 活动开始时间
     */
    public Date getActivityStart() {
        return activityStart;
    }

    /**
     * 设置活动开始时间
     *
     * @param activityStart 活动开始时间
     */
    public void setActivityStart(Date activityStart) {
        this.activityStart = activityStart;
    }

    /**
     * 获取活动结束时间
     *
     * @return activity_end - 活动结束时间
     */
    public Date getActivityEnd() {
        return activityEnd;
    }

    /**
     * 设置活动结束时间
     *
     * @param activityEnd 活动结束时间
     */
    public void setActivityEnd(Date activityEnd) {
        this.activityEnd = activityEnd;
    }

    /**
     * 获取问卷访问网址
     *
     * @return uestionnaire_uri - 问卷访问网址
     */
    public String getUestionnaireUri() {
        return uestionnaireUri;
    }

    /**
     * 设置问卷访问网址
     *
     * @param uestionnaireUri 问卷访问网址
     */
    public void setUestionnaireUri(String uestionnaireUri) {
        this.uestionnaireUri = uestionnaireUri;
    }

    /**
     * 获取活动邀请码
     *
     * @return activity_porn - 活动邀请码
     */
    public String getActivityPorn() {
        return activityPorn;
    }

    /**
     * 设置活动邀请码
     *
     * @param activityPorn 活动邀请码
     */
    public void setActivityPorn(String activityPorn) {
        this.activityPorn = activityPorn;
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
     * 获取是否删除 0 否  1 是
     *
     * @return deleted - 是否删除 0 否  1 是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除 0 否  1 是
     *
     * @param deleted 是否删除 0 否  1 是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取发起人类型 0咨询师   1平台调研员(需要审批才会生产链接与邀请码)
     *
     * @return Initiator_type - 发起人类型 0咨询师   1平台调研员(需要审批才会生产链接与邀请码)
     */
    public Integer getInitiatorType() {
        return initiatorType;
    }

    /**
     * 设置发起人类型 0咨询师   1平台调研员(需要审批才会生产链接与邀请码)
     *
     * @param initiatorType 发起人类型 0咨询师   1平台调研员(需要审批才会生产链接与邀请码)
     */
    public void setInitiatorType(Integer initiatorType) {
        this.initiatorType = initiatorType;
    }

    /**
     * 获取活动状态(供需审批的类型使用)
     *
     * @return activity_status - 活动状态(供需审批的类型使用)
     */
    public Integer getActivityStatus() {
        return activityStatus;
    }

    /**
     * 设置活动状态(供需审批的类型使用)
     *
     * @param activityStatus 活动状态(供需审批的类型使用)
     */
    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
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
     * 获取创建人id
     *
     * @return userId - 创建人id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置创建人id
     *
     * @param userId 创建人id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}