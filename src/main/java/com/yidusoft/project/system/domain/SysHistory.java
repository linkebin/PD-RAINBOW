package com.yidusoft.project.system.domain;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_history")
public class SysHistory {
    /**
     * 操作历史记录
     */
    @Id
    @Column(name = "history_id")
    private String historyId;

    private String type;

    @Column(name = "history_data")
    private String historyData;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取操作历史记录
     *
     * @return history_id - 操作历史记录
     */
    public String getHistoryId() {
        return historyId;
    }

    /**
     * 设置操作历史记录
     *
     * @param historyId 操作历史记录
     */
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return history_data
     */
    public String getHistoryData() {
        return historyData;
    }

    /**
     * @param historyData
     */
    public void setHistoryData(String historyData) {
        this.historyData = historyData;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
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