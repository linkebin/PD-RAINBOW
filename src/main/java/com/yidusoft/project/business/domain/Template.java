package com.yidusoft.project.business.domain;

import javax.persistence.*;
import java.util.Date;

public class Template {
    @Id
    @Column(name = "id_")
    private String id;

    @Column(name = "question_type")
    private String questionType;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private String age;

    @Column(name = "religion")
    private String religion;

    @Column(name = "marriage")
    private String marriage;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "template_name")
    private String templateName;
    /**
     * @return id_
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
     * @return type
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * @param questionType
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    /**
     * @return template_sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return marriage
     */
    public String getMarriage() {
        return marriage;
    }

    /**
     * @param marriage
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return userId
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
     * @return userId
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}