package com.yidusoft.project.questionnaire.domain;

import javax.persistence.*;

@Table(name = "questionnaire_scene")
public class QuestionnaireScene {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 场景ID
     */
    @Column(name = "scene_id")
    private String sceneId;

    /**
     * 问卷ID
     */
    @Column(name = "questionnaire_id")
    private String questionnaireId;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取场景ID
     *
     * @return scene_id - 场景ID
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 设置场景ID
     *
     * @param sceneId 场景ID
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 获取问卷ID
     *
     * @return questionnaire_id - 问卷ID
     */
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * 设置问卷ID
     *
     * @param questionnaireId 问卷ID
     */
    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
}