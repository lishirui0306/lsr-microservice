package cn.lsr.core;

import cn.lsr.entity.User;
import org.springframework.batch.item.ItemProcessor;


/**
 * @Description: cvs操作
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class CvsItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        //逻辑处理
        return null;
    }
}
