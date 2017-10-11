package com.yidusoft.project.questionnaire.domain;

import javax.persistence.*;

@Table(name = "questionnaire_question_factor")
public class QuestionnaireQuestionFactor {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "question_id")
    private String questionId;

    /**
     * 问卷id
     */
    @Column(name = "questionnaire_id")
    private String questionnaireId;

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
     * @return question_id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取问卷id
     *
     * @return questionnaire_id - 问卷id
     */
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * 设置问卷id
     *
     * @param questionnaireId 问卷id
     */
    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
}