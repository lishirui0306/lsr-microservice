package cn.lsr.user.mapper.user;

import cn.lsr.core.config.mapper.MyMapper;
import cn.lsr.user.entity.user.User;

public interface UserMapper extends MyMapper<User> {

    User selectByName(String username);

    User selectUserByName(String username);

    User selecTest(String username);

}