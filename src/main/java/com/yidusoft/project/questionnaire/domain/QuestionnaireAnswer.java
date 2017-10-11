package com.yidusoft.project.questionnaire.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "questionnaire_answer")
public class QuestionnaireAnswer {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除 0 否 1 是
     */
    private Integer deleted;

    /**
     * 问题id
     */
    @Column(name = "question_id")
    private String questionId;

    /**
     * 答案评分
     */
    @Column(name = "answer_score")
    private Integer answerScore;

    /**
     * 来访人id 或者  参与活动人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 问卷使用id
     */
    @Column(name = "acquisition_id")
    private String acquisitionId;

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
     * 获取是否删除 0 否 1 是
     *
     * @return deleted - 是否删除 0 否 1 是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除 0 否 1 是
     *
     * @param deleted 是否删除 0 否 1 是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取问题id
     *
     * @return question_id - 问题id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置问题id
     *
     * @param questionId 问题id
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取答案评分
     *
     * @return answer_score - 答案评分
     */
    public Integer getAnswerScore() {
        return answerScore;
    }

    /**
     * 设置答案评分
     *
     * @param answerScore 答案评分
     */
    public void setAnswerScore(Integer answerScore) {
        this.answerScore = answerScore;
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
     * 获取问卷使用id
     *
     * @return acquisition_id - 问卷使用id
     */
    public String getAcquisitionId() {
        return acquisitionId;
    }

    /**
     * 设置问卷使用id
     *
     * @param acquisitionId 问卷使用id
     */
    public void setAcquisitionId(String acquisitionId) {
        this.acquisitionId = acquisitionId;
    }
}