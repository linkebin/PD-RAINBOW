package com.yidusoft.project.questionnaire.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "questionnaire_type")
public class QuestionnaireType {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 类型编号
     */
    @Column(name = "questionnaire_type_code")
    private String questionnaireTypeCode;

    /**
     * 类型名称
     */
    @Column(name = "questionnaire_type_name")
    private String questionnaireTypeName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除 0  否 1 是
     */
    private Integer deleted;

    /**
     * 父类型
     */
    private String pid;

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
     * 获取类型编号
     *
     * @return questionnaire_type_code - 类型编号
     */
    public String getQuestionnaireTypeCode() {
        return questionnaireTypeCode;
    }

    /**
     * 设置类型编号
     *
     * @param questionnaireTypeCode 类型编号
     */
    public void setQuestionnaireTypeCode(String questionnaireTypeCode) {
        this.questionnaireTypeCode = questionnaireTypeCode;
    }

    /**
     * 获取类型名称
     *
     * @return questionnaire_type_name - 类型名称
     */
    public String getQuestionnaireTypeName() {
        return questionnaireTypeName;
    }

    /**
     * 设置类型名称
     *
     * @param questionnaireTypeName 类型名称
     */
    public void setQuestionnaireTypeName(String questionnaireTypeName) {
        this.questionnaireTypeName = questionnaireTypeName;
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
     * 获取是否删除 0  否 1 是
     *
     * @return deleted - 是否删除 0  否 1 是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除 0  否 1 是
     *
     * @param deleted 是否删除 0  否 1 是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取父类型
     *
     * @return pid - 父类型
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父类型
     *
     * @param pid 父类型
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
}