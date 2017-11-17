package com.yidusoft.project.questionnaire.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

public class Questionnaire {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 问卷编号
     */
    @Column(name = "questionnaire_code")
    private String questionnaireCode;

    /**
     * 问卷名称
     */
    @Column(name = "questionnaire_name")
    private String questionnaireName;

    /**
     * 上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "shelf_time")
    private Date shelfTime;

    /**
     * 问题答案正反序
     */
    @Column(name = "question_sequence")
    private Integer questionSequence;

    /**
     * 问卷状态（1 设计中、2 使用中、3 已失效）
     */
    @Column(name = "questionnaire_state")
    private Integer questionnaireState;

    /**
     * 使用模板类型（1 横版、2 竖直版）
     */
    @Column(name = "answer_model_type")
    private Integer answerModelType;


    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 引导语
     */
    @Column(name = "guide")
    private String guide;

    /**
     * 是否删除 0 否 1 是
     */
    private Integer deleted;

    /**
     * 问卷类型id
     */
    @Column(name = "questionnaire_type")
    private String questionnaireType;

    /**
     * 使用量表（模板）
     */
    @Column(name = "gauge_id")
    private String gaugeId;

    /**
     * 获取问卷权限
     * @return
     */
    public Integer getQuestionnairePermission() {
        return questionnairePermission;
    }

    /**
     * 设置问卷权限
     * @param questionnairePermission
     */
    public void setQuestionnairePermission(Integer questionnairePermission) {
        this.questionnairePermission = questionnairePermission;
    }

    /**
     * 问卷可见权限 1：全部  2：指定咨询师

     */
    @Column(name = "questionnaire_permission")
    private Integer questionnairePermission;

    /**
     * 注意事项
     */
    private String remarks;


    @Transient
    private  String questionnaireTypeName;

    //量表名称
    @Transient
    private String gaugeName;

    public String getGaugeName() {
        return gaugeName;
    }

    public void setGaugeName(String gaugeName) {
        this.gaugeName = gaugeName;
    }

    public String getQuestionnaireTypeName() {
        return questionnaireTypeName;
    }

    public void setQuestionnaireTypeName(String questionnaireTypeName) {
        this.questionnaireTypeName = questionnaireTypeName;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
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
     * 获取问卷编号
     *
     * @return questionnaire_code - 问卷编号
     */
    public String getQuestionnaireCode() {
        return questionnaireCode;
    }

    /**
     * 设置问卷编号
     *
     * @param questionnaireCode 问卷编号
     */
    public void setQuestionnaireCode(String questionnaireCode) {
        this.questionnaireCode = questionnaireCode;
    }

    /**
     * 获取问卷名称
     *
     * @return questionnaire_name - 问卷名称
     */
    public String getQuestionnaireName() {
        return questionnaireName;
    }

    /**
     * 设置问卷名称
     *
     * @param questionnaireName 问卷名称
     */
    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    /**
     * 获取上架时间
     *
     * @return shelf_time - 上架时间
     */
    public Date getShelfTime() {
        return shelfTime;
    }

    /**
     * 设置上架时间
     *
     * @param shelfTime 上架时间
     */
    public void setShelfTime(Date shelfTime) {
        this.shelfTime = shelfTime;
    }

    /**
     * 获取问题答案正反序
     *
     * @return question_sequence - 问题答案正反序
     */
    public Integer getQuestionSequence() {
        return questionSequence;
    }

    /**
     * 设置问题答案正反序
     *
     * @param questionSequence 问题答案正反序
     */
    public void setQuestionSequence(Integer questionSequence) {
        this.questionSequence = questionSequence;
    }

    /**
     * 获取问卷状态（1 设计中、2 使用中、3 已失效）
     *
     * @return questionnaire_state - 问卷状态（1 设计中、2 使用中、3 已失效）
     */
    public Integer getQuestionnaireState() {
        return questionnaireState;
    }

    /**
     * 设置问卷状态（1 设计中、2 使用中、3 已失效）
     *
     * @param questionnaireState 问卷状态（1 设计中、2 使用中、3 已失效）
     */
    public void setQuestionnaireState(Integer questionnaireState) {
        this.questionnaireState = questionnaireState;
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
     * 获取修改人
     *
     * @return modifier - 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改人
     *
     * @param modifier 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
     * 获取问卷类型id
     *
     * @return questionnaire_type - 问卷类型id
     */
    public String getQuestionnaireType() {
        return questionnaireType;
    }

    /**
     * 设置问卷类型id
     *
     * @param questionnaireType 问卷类型id
     */
    public void setQuestionnaireType(String questionnaireType) {
        this.questionnaireType = questionnaireType;
    }

    /**
     * 获取使用量表（模板）
     *
     * @return gauge_id - 使用量表（模板）
     */
    public String getGaugeId() {
        return gaugeId;
    }

    /**
     * 设置使用量表（模板）
     *
     * @param gaugeId 使用量表（模板）
     */
    public void setGaugeId(String gaugeId) {
        this.gaugeId = gaugeId;
    }

    /**
     * 获取注意事项
     *
     * @return remarks - 注意事项
     */
    public String getRemarks() {
        return remarks;
    }

    public Integer getAnswerModelType() {
        return answerModelType;
    }

    public void setAnswerModelType(Integer answerModelType) {
        this.answerModelType = answerModelType;
    }

    /**
     * 设置注意事项
     *
     * @param remarks 注意事项
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}