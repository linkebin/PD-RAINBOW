package com.yidusoft.project.business.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "visiting_record_file")
public class VisitingRecordFile {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 来访记录id
     */
    @Column(name = "record_id")
    private String recordId;

    /**
     * 文件地址
     */
    @Column(name = "file_uri")
    private String fileUri;

    @Column(name = "file_type")
    private String fileType;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 是否删除0 否 1是
     */
    private Integer deleted;

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
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取来访记录id
     *
     * @return record_id - 来访记录id
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * 设置来访记录id
     *
     * @param recordId 来访记录id
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取文件地址
     *
     * @return file_uri - 文件地址
     */
    public String getFileUri() {
        return fileUri;
    }

    /**
     * 设置文件地址
     *
     * @param fileUri 文件地址
     */
    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
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
     * 获取是否删除0 否 1是
     *
     * @return deleted - 是否删除0 否 1是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除0 否 1是
     *
     * @param deleted 是否删除0 否 1是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}