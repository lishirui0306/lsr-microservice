package cn.lsr.user.service.user.iml;

import cn.lsr.user.entity.user.User;
import cn.lsr.user.mapper.user.UserMapper;
import cn.lsr.user.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * = =
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Service
public class userServiceIml implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public User selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public User selecTest(String username) {
        return userMapper.selecTest(username);
    }
}
