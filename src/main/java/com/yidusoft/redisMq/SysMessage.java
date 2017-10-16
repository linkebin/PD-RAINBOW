package com.yidusoft.redisMq;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class SysMessage  implements  Serializable{

    private String id;

    /**
     * 发送者编号
     */
    private String sendid;

    /**
     * 接受者编号
     */
    private String recid;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 站内信内容
     */
    private String message;

    /**
     * 站内信的查看状态
     */
    private Integer status;

    /**
     * 发送时间
     */
    @Column(name = "PDate")
    private Date pdate;

    /**
     * 删除标记  
     */
    private Integer deleted;

    /**
     * 消息链接
     */
    private String url;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 消息对应对象ID
     */
    private String objId;

    /**
     * 是否推送
     */
    private Integer push;

    public Integer getPush() {
        return push;
    }

    public void setPush(Integer push) {
        this.push = push;
    }

    /**
     * @return id
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
     * 获取发送者编号
     *
     * @return SendID - 发送者编号
     */
    public String getSendid() {
        return sendid;
    }

    /**
     * 设置发送者编号
     *
     * @param sendid 发送者编号
     */
    public void setSendid(String sendid) {
        this.sendid = sendid;
    }

    /**
     * 获取接受者编号
     *
     * @return RecID - 接受者编号
     */
    public String getRecid() {
        return recid;
    }

    /**
     * 设置接受者编号
     *
     * @param recid 接受者编号
     */
    public void setRecid(String recid) {
        this.recid = recid;
    }

    /**
     * 获取消息标题
     *
     * @return message_title - 消息标题
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * 设置消息标题
     *
     * @param messageTitle 消息标题
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    /**
     * 获取站内信内容
     *
     * @return Message - 站内信内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置站内信内容
     *
     * @param message 站内信内容
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取站内信的查看状态
     *
     * @return Status - 站内信的查看状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置站内信的查看状态
     *
     * @param status 站内信的查看状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取发送时间
     *
     * @return PDate - 发送时间
     */
    public Date getPdate() {
        return pdate;
    }

    /**
     * 设置发送时间
     *
     * @param pdate 发送时间
     */
    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    /**
     * 获取删除标记  
     *
     * @return deleted - 删除标记  
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标记  
     *
     * @param deleted 删除标记  
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取消息链接
     *
     * @return url - 消息链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置消息链接
     *
     * @param url 消息链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取消息类型
     *
     * @return message_type - 消息类型
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * 设置消息类型
     *
     * @param messageType 消息类型
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * 获取消息对应对象ID
     *
     * @return obj_id - 消息对应对象ID
     */
    public String getObjId() {
        return objId;
    }

    /**
     * 设置消息对应对象ID
     *
     * @param objId 消息对应对象ID
     */
    public void setObjId(String objId) {
        this.objId = objId;
    }

    public SysMessage(String id, String sendid, String recid, String messageTitle, String message, Integer status, Date pdate, Integer deleted, String url, String messageType, String objId) {
        this.id = id;
        this.sendid = sendid;
        this.recid = recid;
        this.messageTitle = messageTitle;
        this.message = message;
        this.status = status;
        this.pdate = pdate;
        this.deleted = deleted;
        this.url = url;
        this.messageType = messageType;
        this.objId = objId;
    }

    public SysMessage(String id, String sendid, String recid, String messageTitle, String message, Integer status, Date pdate, String url, String messageType, String objId) {
        this.id = id;
        this.sendid = sendid;
        this.recid = recid;
        this.messageTitle = messageTitle;
        this.message = message;
        this.status = status;
        this.pdate = pdate;
        this.url = url;
        this.messageType = messageType;
        this.objId = objId;
    }

    public SysMessage(String sendid, String recid, String messageTitle, String message, String url, String objId) {
        this.sendid = sendid;
        this.recid = recid;
        this.messageTitle = messageTitle;
        this.message = message;
        this.url = url;
        this.objId = objId;
    }

    public SysMessage(String sendid, String recid, String messageTitle, String url, String messageType) {
        this.sendid = sendid;
        this.recid = recid;
        this.messageTitle = messageTitle;
        this.url = url;
        this.messageType = messageType;
    }

    public SysMessage() {
    }

    @Override
    public String toString() {
        return "SysMessage{" +
                "id='" + id + '\'' +
                ", sendid='" + sendid + '\'' +
                ", recid='" + recid + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", pdate=" + pdate +
                ", deleted=" + deleted +
                ", url='" + url + '\'' +
                ", messageType='" + messageType + '\'' +
                ", objId='" + objId + '\'' +
                ", push=" + push +
                '}';
    }
}