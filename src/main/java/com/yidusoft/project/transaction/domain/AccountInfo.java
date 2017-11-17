package com.yidusoft.project.transaction.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "account_info")
public class AccountInfo {
    /**
     * 主键ID_
     */
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 流水号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 操作日期
     */
    @Column(name = "account_date")
    private Date accountDate;

    /**
     * 备注信息
     */
    @Column(name = "account_remarks")
    private String accountRemarks;

    /**
     * 购买数量
     */
    @Column(name = "buy_total")
    private String buyTotal;

    /**
     * 使用数量
     */
    @Column(name = "account_total")
    private String accountTotal;

    /**
     * 消费金额
     */
    @Column(name = "cost_money")
    private String costMoney;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 问卷剩余量
     */
    @Column(name = "account_surplus")
    private Integer accountSurplus;

    /**
     * 获取主键ID_
     *
     * @return id_ - 主键ID_
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID_
     *
     * @param id 主键ID_
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取流水号
     *
     * @return serial_number - 流水号
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置流水号
     *
     * @param serialNumber 流水号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取操作日期
     *
     * @return account_date - 操作日期
     */
    public Date getAccountDate() {
        return accountDate;
    }

    /**
     * 设置操作日期
     *
     * @param accountDate 操作日期
     */
    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    /**
     * 获取备注信息
     *
     * @return account_remarks - 备注信息
     */
    public String getAccountRemarks() {
        return accountRemarks;
    }

    /**
     * 设置备注信息
     *
     * @param accountRemarks 备注信息
     */
    public void setAccountRemarks(String accountRemarks) {
        this.accountRemarks = accountRemarks;
    }

    /**
     * 获取购买数量
     *
     * @return buy_total - 购买数量
     */
    public String getBuyTotal() {
        return buyTotal;
    }

    /**
     * 设置购买数量
     *
     * @param buyTotal 购买数量
     */
    public void setBuyTotal(String buyTotal) {
        this.buyTotal = buyTotal;
    }

    /**
     * 获取使用数量
     *
     * @return account_total - 使用数量
     */
    public String getAccountTotal() {
        return accountTotal;
    }

    /**
     * 设置使用数量
     *
     * @param accountTotal 使用数量
     */
    public void setAccountTotal(String accountTotal) {
        this.accountTotal = accountTotal;
    }

    /**
     * 获取消费金额
     *
     * @return cost_money - 消费金额
     */
    public String getCostMoney() {
        return costMoney;
    }

    /**
     * 设置消费金额
     *
     * @param costMoney 消费金额
     */
    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
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
     * 获取问卷剩余量
     *
     * @return account_surplus - 问卷剩余量
     */
    public Integer getAccountSurplus() {
        return accountSurplus;
    }

    /**
     * 设置问卷剩余量
     *
     * @param accountSurplus 问卷剩余量
     */
    public void setAccountSurplus(Integer accountSurplus) {
        this.accountSurplus = accountSurplus;
    }
}