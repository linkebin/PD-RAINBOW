package com.yidusoft.project.questionnaire.domain;

import javax.persistence.*;

@Table(name = "questionnaire_tag_middle")
public class QuestionnaireTagMiddle {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "questionnaire_id")
    private String questionnaireId;

    @Column(name = "questionnaire_tag_id")
    private String questionnaireTagId;

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
     * @return questionnaire_id
     */
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * @param questionnaireId
     */
    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    /**
     * @return questionnaire_tag_id
     */
    public String getQuestionnaireTagId() {
        return questionnaireTagId;
    }

    /**
     * @param questionnaireTagId
     */
    public void setQuestionnaireTagId(String questionnaireTagId) {
        this.questionnaireTagId = questionnaireTagId;
    }
}