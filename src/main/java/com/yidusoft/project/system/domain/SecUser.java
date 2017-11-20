package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sec_user")
public class SecUser implements Serializable {
    private static final long serialVersionUID = -916522239749530671L;
    /**
     * 主键ID
     */
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 账号
     */
    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "ACCOUNT_TYPE")
    private Integer accountType;

    /**
     * 密码
     */
    @Column(name = "USER_PASS")
    private String userPass;


    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    /**
     * 用户名称
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 所属组织
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 手机号码
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 地址
     */
    @Column(name = "ADDR")
    private String addr;

    /**
     * 状态 0 不可用 1 可用
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 性别 0 女 1 男
     */
    @Column(name = "SEX")
    private Integer sex;

    /**
     * 创建人
     */
    @Column(name = "CREATOR")
    private String creator;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 删除标识
     */
    @Column(name = "DELETED")
    private Integer deleted;

    /**
     * 用户头像
     */
    @Column(name = "HEAD_IMG")
    private String headImg;

    /**
     * 获取资质认证
     * @return
     */
    public String getCertification() {
        return certification;
    }

    /**
     * 设置资质认证
     * @param certification
     */
    public void setCertification(String certification) {
        this.certification = certification;
    }

    /**
     * 资质认证
     */
    @Column(name="CERTIFICATION")
    private String certification;

    @Column(name = "COMPANY_ID")
    private String companyId;

    @Column(name = "inviter_code")
    private String inviterCode;

    @Column(name = "inviter_user")
    private String inviterUser;

    @Column(name = "channel_id")
    private String channelId;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public String getInviterUser() {
        return inviterUser;
    }

    public void setInviterUser(String inviterUser) {
        this.inviterUser = inviterUser;
    }

    /**
     * 是否添加
     */
    @Transient
    @Column(name = "GRANTED")
    private Integer granted;

    @Transient
    @Column(name = "MEMBERId")
    private String  memberId;

    @Transient
    private String  orgName;

    @Transient
    private String msgCode;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取主键ID
     *
     * @return ID_ - 主键ID
     */
    public String getId() {
        return id;
    }


    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取账号
     *
     * @return ACCOUNT - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户名称
     *
     * @return USER_NAME - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取所属组织
     *
     * @return ORG_ID - 所属组织
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置所属组织
     *
     * @param orgId 所属组织
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取手机号码
     *
     * @return MOBILE - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取邮箱
     *
     * @return EMAIL - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取地址
     *
     * @return ADDR - 地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 设置地址
     *
     * @param addr 地址
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 获取状态 0 不可用 1 可用
     *
     * @return STATUS - 状态 0 不可用 1 可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0 不可用 1 可用
     *
     * @param status 状态 0 不可用 1 可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取性别 0 女 1 男
     *
     * @return SEX - 性别 0 女 1 男
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
     * 获取创建人
     *
     * @return CREATOR - 创建人
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
     * @return CREATE_TIME - 创建时间
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
     * 获取删除标识
     *
     * @return DELETED - 删除标识
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识
     *
     * @param deleted 删除标识
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Integer getGranted() { return granted; }


    public void setGranted(Integer granted) { this.granted = granted; }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Transient
    private String vrifyCode;


    public String getVrifyCode() {
        return vrifyCode;
    }

    public void setVrifyCode(String vrifyCode) {
        this.vrifyCode = vrifyCode;
    }
}