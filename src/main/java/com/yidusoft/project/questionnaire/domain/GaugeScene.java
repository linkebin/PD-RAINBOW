package com.yidusoft.project.questionnaire.domain;

import javax.persistence.*;

@Table(name = "gauge_scene")
public class GaugeScene {
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
     * 量表ID
     */
    @Column(name = "gauge_id")
    private String gaugeId;

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
     * 获取量表ID
     *
     * @return gauge_id - 量表ID
     */
    public String getGaugeId() {
        return gaugeId;
    }

    /**
     * 设置量表ID
     *
     * @param gaugeId 量表ID
     */
    public void setGaugeId(String gaugeId) {
        this.gaugeId = gaugeId;
    }
}