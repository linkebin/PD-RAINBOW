package com.yidusoft.project.questionnaire.domain;

import java.util.Date;
import javax.persistence.*;

public class Scene {
    /**
     * id
     */
    @Id
    @Column(name = "id_")
    private String id;




    /**
     * 场景编号
     */
    @Column(name = "scene_code")
    private String sceneCode;

    /**
     * 场景名称
     */
    @Column(name = "scene_name")
    private String sceneName;

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
     * 删除
     */
    private String deleted;

    /**
     * 问卷的数量
     */
    @Transient
    private Integer nums;

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    /**
     * 获取id
     *
     * @return id_ - id
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
     *获取场景编号
     * @return scene_code 场景编号
     */

    public String getSceneCode() {
        return sceneCode;
    }

    /**
     * 设置场景编号
     * @param sceneCode 场景编号
     */

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    /**
     * 获取场景名称
     *
     * @return scene_name - 场景名称
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * 设置场景名称
     *
     * @param sceneName 场景名称
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
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
     * 获取删除
     *
     * @return deleted - 删除
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * 设置删除
     *
     * @param deleted 删除
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}