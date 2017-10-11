package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "com_accessory")
public class ComAccessory {
    @Id
    @Column(name = "PK_ID")
    private String pkId;

    /**
     * 附件编号
     */
    @Column(name = "FILE_CODE")
    private String fileCode;

    /**
     * 附件路径
     */
    @Column(name = "FILE_PATH")
    private String filePath;

    /**
     * 文件名称
     */
    @Column(name = "FILE_NAME")
    private String fileName;

    /**
     * 文件类型 取英文简称 例如图片为“img”
     */
    @Column(name = "FILE_TYPE")
    private String fileType;

    /**
     * 是否删除 1是0否
     */
    @Column(name = "DELETED")
    private Integer deleted;

    /**
     * 上传时间
     */
    @Column(name = "UPLOAD_TIME")
    private Date uploadTime;

    /**
     * 附件所属
     */
    @Column(name = "BELONG_ID")
    private String belongId;

    /**
     * 上传人ID
     */
    @Column(name = "UPLOAD_PERSON")
    private String uploadPerson;

    /**
     * 上姓名
     */
    @Column(name = "UPLOAD_PERSON_NAME")
    private String uploadPersonName;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * @return PK_ID
     */
    public String getPkId() {
        return pkId;
    }

    /**
     * @param pkId
     */
    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    /**
     * 获取附件编号
     *
     * @return FILE_CODE - 附件编号
     */
    public String getFileCode() {
        return fileCode;
    }

    /**
     * 设置附件编号
     *
     * @param fileCode 附件编号
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    /**
     * 获取附件路径
     *
     * @return FILE_PATH - 附件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置附件路径
     *
     * @param filePath 附件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取文件名称
     *
     * @return FILE_NAME - 文件名称
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
     * 获取文件类型 取英文简称 例如图片为“img”
     *
     * @return FILE_TYPE - 文件类型 取英文简称 例如图片为“img”
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型 取英文简称 例如图片为“img”
     *
     * @param fileType 文件类型 取英文简称 例如图片为“img”
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取是否删除 1是0否
     *
     * @return DELETED - 是否删除 1是0否
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除 1是0否
     *
     * @param deleted 是否删除 1是0否
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取上传时间
     *
     * @return UPLOAD_TIME - 上传时间
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 设置上传时间
     *
     * @param uploadTime 上传时间
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取附件所属
     *
     * @return BELONG_ID - 附件所属
     */
    public String getBelongId() {
        return belongId;
    }

    /**
     * 设置附件所属
     *
     * @param belongId 附件所属
     */
    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    /**
     * 获取上传人ID
     *
     * @return UPLOAD_PERSON - 上传人ID
     */
    public String getUploadPerson() {
        return uploadPerson;
    }

    /**
     * 设置上传人ID
     *
     * @param uploadPerson 上传人ID
     */
    public void setUploadPerson(String uploadPerson) {
        this.uploadPerson = uploadPerson;
    }

    /**
     * 获取上姓名
     *
     * @return UPLOAD_PERSON_NAME - 上姓名
     */
    public String getUploadPersonName() {
        return uploadPersonName;
    }

    /**
     * 设置上姓名
     *
     * @param uploadPersonName 上姓名
     */
    public void setUploadPersonName(String uploadPersonName) {
        this.uploadPersonName = uploadPersonName;
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}