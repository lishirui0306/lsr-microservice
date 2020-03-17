package cn.lsr.repository;

import cn.lsr.entity.ConsumeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: 消费记录
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Repository
public interface ConsumeRecordRepository extends JpaRepository<ConsumeRecord,Integer> {
}
