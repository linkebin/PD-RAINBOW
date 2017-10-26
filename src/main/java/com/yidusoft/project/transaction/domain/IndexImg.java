package com.yidusoft.project.transaction.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "index_img")
public class IndexImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "img_type")
    private Integer imgType;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "create_time")
    private Date createTime;

    private Integer state;

    private String creator;

    private Integer deleted;

    /**
     * @return id
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
     * @return img_type
     */
    public Integer getImgType() {
        return imgType;
    }

    /**
     * @param imgType
     */
    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    /**
     * @return object_id
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @param objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * @return img_path
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
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