package cn.lsr.user.config.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * = = 密码加密工具类
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
public class PasswordUtil {
    /**
     *  获取加密后的密码
     * @param password  密码
     * @return
     */
    public static String getPassword(String password){
        Md5Hash md5Hash = new Md5Hash(password,null,2);
        return md5Hash.toString();
    }
}
