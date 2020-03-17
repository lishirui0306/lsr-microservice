package cn.lsr.repository;

import cn.lsr.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户信息
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
}
