package com.yidusoft.project.system.domain;

import java.util.Date;

/**
 * Created by zcb on 2017/8/29.
 */
public class SecOrgForParameter {


        /**
         * 组织ID
         */

        private String id;

        /**
         * 组织编码
         */

        private String orgCode;

        /**
         * 组织名称
         */
        private String orgName;

        /**
         * 父组织ID
         */
        private String parentId;

        /**
         * 创建人
         */
        private String creator;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 删除标识
         */
        private Integer deleted;

        private String userId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}