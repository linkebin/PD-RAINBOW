package com.yidusoft.project.questionnaire.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "questionnaire_tag")
public class QuestionnaireTag {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 标签编号
     */
    @Column(name = "tag_code")
    private String tagCode;

    /**
     * 标签名称
     */
    @Column(name = "tag_name")
    private String tagName;

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
     * 是否删除
     */
    private Integer deleted;

    /**
     * 标签说明
     */
    @Column(name = "tag_explain")
    private String tagExplain;

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
     * 获取标签编号
     *
     * @return tag_code - 标签编号
     */
    public String getTagCode() {
        return tagCode;
    }

    /**
     * 设置标签编号
     *
     * @param tagCode 标签编号
     */
    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    /**
     * 获取标签名称
     *
     * @return tag_name - 标签名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名称
     *
     * @param tagName 标签名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
     * 获取是否删除
     *
     * @return deleted - 是否删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除
     *
     * @param deleted 是否删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取标签说明
     *
     * @return tag_explain - 标签说明
     */
    public String getTagExplain() {
        return tagExplain;
    }

    /**
     * 设置标签说明
     *
     * @param tagExplain 标签说明
     */
    public void setTagExplain(String tagExplain) {
        this.tagExplain = tagExplain;
    }
}