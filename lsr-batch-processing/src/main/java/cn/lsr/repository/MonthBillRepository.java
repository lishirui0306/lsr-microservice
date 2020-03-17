package cn.lsr.repository;

import cn.lsr.entity.MonthBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 月账单
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Repository
public interface MonthBillRepository extends JpaRepository<MonthBill,Integer> {
    @Query("select m from MonthBill m where m.isNotice = false and m.isPaid = false and m.createTime between ?1 and ?2")
    List<MonthBill> seleMothBillNoPlayAll(Date start, Date end);
}
