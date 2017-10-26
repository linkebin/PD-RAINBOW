package com.yidusoft.project.business.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

public class Schedule {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 咨询师
     */
    @Column(name = "consultant_id")
    private String consultantId;

    /**
     * 来访者
     */
    @Column(name = "visitor_id")
    private String visitorId;

    /**
     * 来访时间
     */
    @Column(name = "visitor_time")
    private Date visitorTime;

    @Transient
    private String visitorTimeStr;

    public String getVisitorTimeStr() {
        return visitorTimeStr;
    }

    public void setVisitorTimeStr(String visitorTimeStr) {
        this.visitorTimeStr = visitorTimeStr;
    }

    /**
     * 来访者姓名
     */
    @Column(name = "visitor_name")
    private String visitorName;

    /**
     * 来访者次数
     */
    @Column(name = "visitor_total")
    private Integer visitorTotal;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    @Column(name = "phone")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 是否删除
     */
    private Integer deleted;

    @Column(name = "describes")
    private String describes;

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

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
     * 获取咨询师
     *
     * @return consultant_id - 咨询师
     */
    public String getConsultantId() {
        return consultantId;
    }

    /**
     * 设置咨询师
     *
     * @param consultantId 咨询师
     */
    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    /**
     * 获取来访者
     *
     * @return visitor_id - 来访者
     */
    public String getVisitorId() {
        return visitorId;
    }

    /**
     * 设置来访者
     *
     * @param visitorId 来访者
     */
    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    /**
     * 获取来访时间
     *
     * @return visitor_time - 来访时间
     */
    public Date getVisitorTime() {
        return visitorTime;
    }

    /**
     * 设置来访时间
     *
     * @param visitorTime 来访时间
     */
    public void setVisitorTime(Date visitorTime) {
        this.visitorTime = visitorTime;
    }

    /**
     * 获取来访者姓名
     *
     * @return visitor_name - 来访者姓名
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * 设置来访者姓名
     *
     * @param visitorName 来访者姓名
     */
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    /**
     * 获取来访者次数
     *
     * @return visitor_total - 来访者次数
     */
    public Integer getVisitorTotal() {
        return visitorTotal;
    }

    /**
     * 设置来访者次数
     *
     * @param visitorTotal 来访者次数
     */
    public void setVisitorTotal(Integer visitorTotal) {
        this.visitorTotal = visitorTotal;
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
}