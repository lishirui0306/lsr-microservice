package cn.lsr.user.service.user;

import cn.lsr.user.entity.user.User;

/**
 * = =
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
public interface UserService {
    User selectByName(String username);
    User selectUserByName(String username);
    User selecTest(String username);
}
