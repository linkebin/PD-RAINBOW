package com.yidusoft.project.questionnaire.domain;


import java.beans.Transient;
import java.util.Date;
import javax.persistence.*;

@Table(name = "data_acquisition")
public class DataAcquisition {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 编号
     */
    @Column(name = "data_code")
    private String dataCode;

    /**
     * 创建人
     */
    @Column(name = "data_user")
    private String dataUser;

    /**
     * 问卷 id
     */
    @Column(name = "data_question")
    private String dataQuestion;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除 0 否 1是
     */
    private Integer deleted;

    /**
     * 来访人id 或者  参与活动人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 参与活动
     */
    @Column(name = "activity_id")
    private String activityId;


    /**
     * 结果
     */
    @Column(name = "data_result")
    private String dataResult;

    /**
     * 总分
     */
    @Column(name = "total_score")
    private Integer totalScore;

    @Transient
    public String getVisitorName() {
        return visitorName;
    }
    @Transient
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    /**
     * 来访者姓名
     */
    @Column(name = "visitor_name")
     private String visitorName;

    /**
     * 来访者使用过的问卷名称
     */
    @Column(name = "questionnaire_name")
    private String questionnaireName;

    @Transient
    public String getQuestionnaireName() {
        return questionnaireName;
    }

    @Transient
    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getDataResult() {
        return dataResult;
    }

    public void setDataResult(String dataResult) {
        this.dataResult = dataResult;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
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
     * 获取数据编号
     *
     * @return data_code - 数据编号
     */
    public String getDataCode() {
        return dataCode;
    }

    /**
     * 设置数据编号
     *
     * @param dataCode 数据编号
     */
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    /**
     * 获取创建人
     *
     * @return data_user - 创建人
     */
    public String getDataUser() {
        return dataUser;
    }

    /**
     * 设置创建人
     *
     * @param dataUser 创建人
     */
    public void setDataUser(String dataUser) {
        this.dataUser = dataUser;
    }

    /**
     * 获取数据内容
     *
     * @return data_question - 数据内容
     */
    public String getDataQuestion() {
        return dataQuestion;
    }

    /**
     * 设置数据内容
     *
     * @param dataQuestion 数据内容
     */
    public void setDataQuestion(String dataQuestion) {
        this.dataQuestion = dataQuestion;
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
     * 获取来访人id 或者  参与活动人id
     *
     * @return user_id - 来访人id 或者  参与活动人id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置来访人id 或者  参与活动人id
     *
     * @param userId 来访人id 或者  参与活动人id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取参与活动
     *
     * @return activity_id - 参与活动
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * 设置参与活动
     *
     * @param activityId 参与活动
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}