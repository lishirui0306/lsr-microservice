package cn.lsr.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 月账单
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Entity
@Table(name = "month_bill")
public class MonthBill {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * 用户ID
     */
    @Column(name = "userId")
    private Integer userId;
    /**
     * 总费用
     */
    @Column(name = "totalFee")
    private BigDecimal totalFee;

    /**
     * 是否已还款
     */
    @Column(name = "isPaid")
    private Boolean isPaid;

    /**
     * 是否通知
     */
    @Column(name = "isNotice")
    private Boolean isNotice;

    /**
     * 账单生成时间
     */
    @Column(name = "createTime")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean getNotice() {
        return isNotice;
    }

    public void setNotice(Boolean notice) {
        isNotice = notice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
