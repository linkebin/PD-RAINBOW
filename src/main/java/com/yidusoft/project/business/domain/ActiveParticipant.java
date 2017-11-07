package com.yidusoft.project.business.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "active_participant")
public class ActiveParticipant {
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 姓名
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别 0 女 1 男
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 填报时间
     */
    @Column(name = "filling_time")
    private Date fillingTime;

    /**
     * 关注度
     */
    @Column(name = "attention_degree")
    private String attentionDegree;

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
     * 获取姓名
     *
     * @return full_name - 姓名
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置姓名
     *
     * @param fullName 姓名
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取性别 0 女 1 男
     *
     * @return sex - 性别 0 女 1 男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 0 女 1 男
     *
     * @param sex 性别 0 女 1 男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
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
     * 获取填报时间
     *
     * @return filling_time - 填报时间
     */
    public Date getFillingTime() {
        return fillingTime;
    }

    /**
     * 设置填报时间
     *
     * @param fillingTime 填报时间
     */
    public void setFillingTime(Date fillingTime) {
        this.fillingTime = fillingTime;
    }

    /**
     * 获取关注度
     *
     * @return attention_degree - 关注度
     */
    public String getAttentionDegree() {
        return attentionDegree;
    }

    /**
     * 设置关注度
     *
     * @param attentionDegree 关注度
     */
    public void setAttentionDegree(String attentionDegree) {
        this.attentionDegree = attentionDegree;
    }
}