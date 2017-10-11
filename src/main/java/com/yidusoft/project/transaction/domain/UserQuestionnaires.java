package com.yidusoft.project.transaction.domain;

import javax.persistence.*;

@Table(name = "user_questionnaires")
public class UserQuestionnaires {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 用户的id(咨询师或调研员)
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 问卷的剩余数量
     */
    @Column(name = "questionnaires_total")
    private Integer questionnairesTotal;

    /**
     * 使用累计数
     */
    @Column(name = "questionnaires_cumulative_total")
    private Integer questionnairesCumulativeTotal;

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
     * 获取用户的id(咨询师或调研员)
     *
     * @return user_id - 用户的id(咨询师或调研员)
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户的id(咨询师或调研员)
     *
     * @param userId 用户的id(咨询师或调研员)
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取问卷的剩余数量
     *
     * @return questionnaires_total - 问卷的剩余数量
     */
    public Integer getQuestionnairesTotal() {
        return questionnairesTotal;
    }

    /**
     * 设置问卷的剩余数量
     *
     * @param questionnairesTotal 问卷的剩余数量
     */
    public void setQuestionnairesTotal(Integer questionnairesTotal) {
        this.questionnairesTotal = questionnairesTotal;
    }

    /**
     * 获取使用累计数
     *
     * @return questionnaires_cumulative_total - 使用累计数
     */
    public Integer getQuestionnairesCumulativeTotal() {
        return questionnairesCumulativeTotal;
    }

    /**
     * 设置使用累计数
     *
     * @param questionnairesCumulativeTotal 使用累计数
     */
    public void setQuestionnairesCumulativeTotal(Integer questionnairesCumulativeTotal) {
        this.questionnairesCumulativeTotal = questionnairesCumulativeTotal;
    }
}