package com.yidusoft.project.questionnaire.domain;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;
public class Gauge {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "gauge_code")
    private String gaugeCode;

    @Column(name = "gauge_type")
    private String gaugeType;

    @Column(name = "gauge_name")
    private String gaugeName;

    @Column(name = "gauge_state")
    private Integer gaugeState;

    @Column(name = "creator")
    private String creator;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "deleted")
    private Integer deleted;

    @Transient
    private  String questionnaireTypeName;

    public String getQuestionnaireTypeName() {
        return questionnaireTypeName;
    }

    public void setQuestionnaireTypeName(String questionnaireTypeName) {
        this.questionnaireTypeName = questionnaireTypeName;
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
     * @return gauge_code
     */
    public String getGaugeCode() {
        return gaugeCode;
    }

    /**
     * @param gaugeCode
     */
    public void setGaugeCode(String gaugeCode) {
        this.gaugeCode = gaugeCode;
    }

    /**
     * @return gauge_type
     */
    public String getGaugeType() {
        return gaugeType;
    }

    /**
     * @param gaugeType
     */
    public void setGaugeType(String gaugeType) {
        this.gaugeType = gaugeType;
    }

    /**
     * @return gauge_name
     */
    public String getGaugeName() {
        return gaugeName;
    }

    /**
     * @param gaugeName
     */
    public void setGaugeName(String gaugeName) {
        this.gaugeName = gaugeName;
    }

    /**
     * @return gauge_state
     */
    public Integer getGaugeState() {
        return gaugeState;
    }

    /**
     * @param gaugeState
     */
    public void setGaugeState(Integer gaugeState) {
        this.gaugeState = gaugeState;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return deleted
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}