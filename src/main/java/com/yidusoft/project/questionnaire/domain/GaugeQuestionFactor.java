package com.yidusoft.project.questionnaire.domain;

import javax.persistence.*;

@Table(name = "gauge_question_factor")
public class GaugeQuestionFactor {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "gauge_id")
    private String gaugeId;

    /**
     * 问题id
     */
    @Column(name = "question_id")
    private String questionId;

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
     * @return gauge_id
     */
    public String getGaugeId() {
        return gaugeId;
    }

    /**
     * @param gaugeId
     */
    public void setGaugeId(String gaugeId) {
        this.gaugeId = gaugeId;
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
}