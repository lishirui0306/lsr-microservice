package cn.lsr.user.mapper.user;

import cn.lsr.core.druid.annotation.DS;
import cn.lsr.core.druid.common.DataSourceType;
import cn.lsr.core.config.mapper.MyMapper;
import cn.lsr.user.entity.user.User;

public interface UserMapper extends MyMapper<User> {

    User selectByName(String username);

    User selectUserByName(String username);
    @DS(DataSourceType.db1)
    User selecTest(String username);

}