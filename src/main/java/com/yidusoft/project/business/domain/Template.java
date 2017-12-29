package com.yidusoft.project.business.domain;

import java.util.Date;
import javax.persistence.*;

public class Template {
    @Id
    @Column(name = "id_")
    private String id;

    @Column(name = "goalIds")
    private String goalids;

    private String sex;

    private String age;

    private String religion;

    private String marriage;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

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
     * @return goalIds
     */
    public String getGoalids() {
        return goalids;
    }

    /**
     * @param goalids
     */
    public void setGoalids(String goalids) {
        this.goalids = goalids;
    }

    /**
     * @return sex
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
     * @return user_id
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
     * @return template_name
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

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}