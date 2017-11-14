package com.yidusoft.project.transaction.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "modify_trajectory")
public class ModifyTrajectory {
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作人
     */
    private String creator;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

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
     * 获取操作内容
     *
     * @return content - 操作内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置操作内容
     *
     * @param content 操作内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取操作人
     *
     * @return creator - 操作人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置操作人
     *
     * @param creator 操作人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取操作时间
     *
     * @return create_time - 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作时间
     *
     * @param createTime 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return product_id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
}