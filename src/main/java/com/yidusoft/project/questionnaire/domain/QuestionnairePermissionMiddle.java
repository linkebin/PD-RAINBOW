package com.yidusoft.project.questionnaire.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by yhdj on 2017/11/14.
 * 可见权限
 */
@Table(name = "questionnaire_permission_middle")
public class QuestionnairePermissionMiddle {
    @Id
    @Column(name = "ID_")
    private String id;


    /**
     * 问卷id
     */
    @Column(name = "questionnaire_id")
    private String questionnaireId;


    /**
     * 咨询师id
     */
    @Column(name = "user_id")
    private String userID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取问卷id
     * @return
     */
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * 设置问卷id
     * @param questionnaireId
     */
    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    /**
     * 获取咨询师id
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 设置咨询师id
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
