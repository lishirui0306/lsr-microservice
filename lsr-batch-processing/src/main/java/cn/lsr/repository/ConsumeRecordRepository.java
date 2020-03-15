package cn.lsr.repository;

import cn.lsr.entity.ConsumeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Repository
public interface ConsumeRecordRepository extends JpaRepository<ConsumeRecord,Integer> {
}
