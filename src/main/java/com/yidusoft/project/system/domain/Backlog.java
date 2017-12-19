package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

public class Backlog {
    /**
     * 待办事项
     */
    @Id
    @Column(name = "ID_")
    private String id;

    private String title;

    private String objid;

    private String url;

    @Column(name = "backlog_type")
    private String backlogType;

    @Column(name = "backlog_status")
    private String backlogStatus;

    /**
     * 代办人id
     */
    @Column(name = "agent_id")
    private String agentId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取待办事项
     *
     * @return ID_ - 待办事项
     */
    public String getId() {
        return id;
    }

    /**
     * 设置待办事项
     *
     * @param id 待办事项
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return objid
     */
    public String getObjid() {
        return objid;
    }

    /**
     * @param objid
     */
    public void setObjid(String objid) {
        this.objid = objid;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return backlog_type
     */
    public String getBacklogType() {
        return backlogType;
    }

    /**
     * @param backlogType
     */
    public void setBacklogType(String backlogType) {
        this.backlogType = backlogType;
    }

    /**
     * @return backlog_status
     */
    public String getBacklogStatus() {
        return backlogStatus;
    }

    /**
     * @param backlogStatus
     */
    public void setBacklogStatus(String backlogStatus) {
        this.backlogStatus = backlogStatus;
    }

    /**
     * 获取代办人id
     *
     * @return agent_id - 代办人id
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * 设置代办人id
     *
     * @param agentId 代办人id
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}