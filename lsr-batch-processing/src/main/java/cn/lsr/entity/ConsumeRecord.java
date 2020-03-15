package cn.lsr.entity;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description: 消费记录
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Entity
@Table(name = "consume_record")
public class ConsumeRecord {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * 用户Id
     */
    @Column(name = "userId")
    private Integer userId;
    /**
     * 花费金额
     */
    @Column(name = "consumption")
    private BigDecimal consumption;
    /**
     * 是否生成账单
     */
    @Column(name = "isGenerateBill")
    private Boolean isGenerateBill;

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

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    public Boolean getGenerateBill() {
        return isGenerateBill;
    }

    public void setGenerateBill(Boolean generateBill) {
        isGenerateBill = generateBill;
    }
}
