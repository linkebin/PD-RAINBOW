package com.yidusoft.project.questionnaire.domain;

import javax.persistence.*;

@Table(name = "gauge_tag_middle")
public class GaugeTagMiddle {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 标签
     */
    @Column(name = "tag_id")
    private String tagId;

    /**
     * 量表id
     */
    @Column(name = "gauge_id")
    private String gaugeId;

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
     * 获取标签
     *
     * @return tag_id - 标签
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * 设置标签
     *
     * @param tagId 标签
     */
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取量表id
     *
     * @return gauge_id - 量表id
     */
    public String getGaugeId() {
        return gaugeId;
    }

    /**
     * 设置量表id
     *
     * @param gaugeId 量表id
     */
    public void setGaugeId(String gaugeId) {
        this.gaugeId = gaugeId;
    }
}