package com.yidusoft.project.channel.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "channel_manage")
public class ChannelManage {
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 渠道编号
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 渠道名称
     */
    @Column(name = "channel_name")
    private String channelName;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系人电话
     */
    @Column(name = "linkman_tell")
    private String linkmanTell;

    /**
     * 联系人住址
     */
    @Column(name = "linkman_addr")
    private String linkmanAddr;

    /**
     * 渠道负责人
     */
    @Column(name = "channel_manager")
    private String channelManager;

    /**
     * 加入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;



    @Column(name = "deleted")
    private Integer deleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * @return id_
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
     * 获取渠道编号
     *
     * @return channel_code - 渠道编号
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置渠道编号
     *
     * @param channelCode 渠道编号
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取渠道名称
     *
     * @return channel_name - 渠道名称
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置渠道名称
     *
     * @param channelName 渠道名称
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取联系人
     *
     * @return linkman - 联系人
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * 设置联系人
     *
     * @param linkman 联系人
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * 获取联系人电话
     *
     * @return linkman_tell - 联系人电话
     */
    public String getLinkmanTell() {
        return linkmanTell;
    }

    /**
     * 设置联系人电话
     *
     * @param linkmanTell 联系人电话
     */
    public void setLinkmanTell(String linkmanTell) {
        this.linkmanTell = linkmanTell;
    }

    /**
     * 获取联系人住址
     *
     * @return linkman_addr - 联系人住址
     */
    public String getLinkmanAddr() {
        return linkmanAddr;
    }

    /**
     * 设置联系人住址
     *
     * @param linkmanAddr 联系人住址
     */
    public void setLinkmanAddr(String linkmanAddr) {
        this.linkmanAddr = linkmanAddr;
    }

    /**
     * 获取渠道负责人
     *
     * @return channel_manager - 渠道负责人
     */
    public String getChannelManager() {
        return channelManager;
    }

    /**
     * 设置渠道负责人
     *
     * @param channelManager 渠道负责人
     */
    public void setChannelManager(String channelManager) {
        this.channelManager = channelManager;
    }

    /**
     * 获取加入时间
     *
     * @return create_time - 加入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置加入时间
     *
     * @param createTime 加入时间
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

    @Transient
    private String joinStartTime;

    @Transient
    private String joinEndTime;

    public String getJoinStartTime() {
        return joinStartTime;
    }

    public void setJoinStartTime(String joinStartTime) {
        this.joinStartTime = joinStartTime;
    }

    public String getJoinEndTime() {
        return joinEndTime;
    }

    public void setJoinEndTime(String joinEndTime) {
        this.joinEndTime = joinEndTime;
    }
}