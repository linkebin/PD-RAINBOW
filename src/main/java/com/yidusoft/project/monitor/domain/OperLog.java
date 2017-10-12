package com.yidusoft.project.monitor.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oper_log")
public class OperLog {
    @Id
    @Column(name = "oper_id")
    private String operId;

    /**
     * 操作类型
     */
    @Column(name = "oper_type")
    private String operType;

    /**
     * 操作内容
     */
    @Column(name = "oper_info")
    private String operInfo;

    /**
     * 操作人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 操作人姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 操作时间
     */
    @Column(name = "oper_time")
    private Date operTime;

    /**
     * @return oper_id
     */
    public String getOperId() {
        return operId;
    }

    /**
     * @param operId
     */
    public void setOperId(String operId) {
        this.operId = operId;
    }

    /**
     * 获取操作类型
     *
     * @return oper_type - 操作类型
     */
    public String getOperType() {
        return operType;
    }

    /**
     * 设置操作类型
     *
     * @param operType 操作类型
     */
    public void setOperType(String operType) {
        this.operType = operType;
    }

    /**
     * 获取操作内容
     *
     * @return oper_info - 操作内容
     */
    public String getOperInfo() {
        return operInfo;
    }

    /**
     * 设置操作内容
     *
     * @param operInfo 操作内容
     */
    public void setOperInfo(String operInfo) {
        this.operInfo = operInfo;
    }

    /**
     * 获取操作人id
     *
     * @return user_id - 操作人id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置操作人id
     *
     * @param userId 操作人id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取操作人姓名
     *
     * @return user_name - 操作人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置操作人姓名
     *
     * @param userName 操作人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作时间
     *
     * @return oper_time - 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 设置操作时间
     *
     * @param operTime 操作时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}