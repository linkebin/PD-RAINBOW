package com.yidusoft.project.monitor.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "login_log")
public class LoginLog {
    @Id
    @Column(name = "login_id")
    private String loginId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录地点
     */
    @Column(name = "login_addr")
    private String loginAddr;

    /**
     * 登录方式
     */
    @Column(name = "login_type")
    private String loginType;

    /**
     * 登录IP
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登录账号类型
     */
    @Column(name = "account_type")
    private Integer accountType;

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     * @return login_id
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
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
     * 获取登录地点
     *
     * @return login_addr - 登录地点
     */
    public String getLoginAddr() {
        return loginAddr;
    }

    /**
     * 设置登录地点
     *
     * @param loginAddr 登录地点
     */
    public void setLoginAddr(String loginAddr) {
        this.loginAddr = loginAddr;
    }

    /**
     * 获取登录方式
     *
     * @return login_type - 登录方式
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * 设置登录方式
     *
     * @param loginType 登录方式
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * 获取登录IP
     *
     * @return login_ip - 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     *
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}