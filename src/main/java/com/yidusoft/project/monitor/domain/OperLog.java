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
     * 操做人ip
     */
    @Column(name = "oper_ip")
    private String operIp;


    /**
     * 访问url
     */
    @Column(name = "oper_url")
    private String operUrl;

    /**
     * 操作时长
     */
    @Column(name = "oper_when_long")
    private String operWhenLong;

    /**
     * 请求类型
     */
    @Column(name = "url_type")
    private String urlType;

    /**
     * 请求方法
     */
    @Column(name = "url_method")
    private String urlMethod;

    /**
     * 请求结果
     */
    @Column(name = "url_result")
    private String urlResult;

    /**
     * 请求参数
     */
    @Column(name = "url_param")
    private String urlParam;

    /**
     * sessionId
     */
    @Column(name = "sessionId")
    private String sessionId;


    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperWhenLong() {
        return operWhenLong;
    }

    public void setOperWhenLong(String operWhenLong) {
        this.operWhenLong = operWhenLong;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrlMethod() {
        return urlMethod;
    }

    public void setUrlMethod(String urlMethod) {
        this.urlMethod = urlMethod;
    }

    public String getUrlResult() {
        return urlResult;
    }

    public void setUrlResult(String urlResult) {
        this.urlResult = urlResult;
    }

    public String getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(String urlParam) {
        this.urlParam = urlParam;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

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