package com.yidusoft.project.channel.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "platform_settlement")
public class PlatformSettlement {
    @Id
    @Column(name = "ID_")
    private String id;

    private String code;

    private String year;

    private String month;

    private String status;

    @Column(name = "should_be")
    private BigDecimal shouldBe;

    private BigDecimal closed;

    private BigDecimal unliquidated;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return should_be
     */
    public BigDecimal getShouldBe() {
        return shouldBe;
    }

    /**
     * @param shouldBe
     */
    public void setShouldBe(BigDecimal shouldBe) {
        this.shouldBe = shouldBe;
    }

    /**
     * @return closed
     */
    public BigDecimal getClosed() {
        return closed;
    }

    /**
     * @param closed
     */
    public void setClosed(BigDecimal closed) {
        this.closed = closed;
    }

    /**
     * @return unliquidated
     */
    public BigDecimal getUnliquidated() {
        return unliquidated;
    }

    /**
     * @param unliquidated
     */
    public void setUnliquidated(BigDecimal unliquidated) {
        this.unliquidated = unliquidated;
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
}