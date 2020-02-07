package cn.lsr.user.core.shiro;

import cn.lsr.user.entity.permission.Permission;
import cn.lsr.user.entity.role.Role;
import cn.lsr.user.entity.user.User;
import cn.lsr.user.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * = = shiro MyRealm配置类
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
public class AuthRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private UserService userService;

    /**
     *  用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("******开始授权******");
        //获取前端输入的用户名
        String username = principalCollection.toString();
        //根据前端输入的用户名查询数据库中对应的记录
        User user = userService.selectByName(username);
        //如果数据库中有该用户名对应的记录，就进行授权操作
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //因为addRoles和addStringPermissions方法需要的参数类型是Collection
            //所以先创建两个collection集合
            Collection<String> rolesCollection = new HashSet<String>();
            Collection<String> perStringCollection = new HashSet<String>();
            //获取user的Role的set集合
            Set<Role> roles = user.getRoles();
            //遍历集合
            for (Role role : roles){
                //将每一个role的name装进collection集合
                rolesCollection.add(role.getRname());
                //获取每一个Role的permission的set集合
                Set<Permission> permissionSet =  role.getPermissions();
                //遍历集合
                for (Permission permission : permissionSet){
                    //将每一个permission的name装进collection集合
                    perStringCollection.add(permission.getPname());
                }
                //为用户授权
                info.addStringPermissions(perStringCollection);
                //为用户授予角色
                info.addRoles(rolesCollection);
            }
            log.info("******授权完成******");
            log.info("你当前拥有"+info.getRoles()+"角色");
            log.info("你当前拥有"+info.getStringPermissions()+"权限");
            return info;
        }else {
            return null;
        }
    }

    /**
     *  认证登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        log.info("*******************************shiro开始认证登录*******************************");
        //获取前端输入的用户名
        String username  = (String) token.getPrincipal();
        //根据用户名查询数据库中对应的记录
        User user = userService.selectByName(username);
        if(user == null) {
            throw new UnknownAccountException("账号不存在！"); // 没找到帐号
        }
        if("1".equals(user.getLocked())) {
            throw new LockedAccountException("账号锁定！"); //帐号锁定
        }
        //当前realm对象的name
        //String realmName = getName();
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );
        log.info("*******************************shiro认证结束*******************************");
        return authenticationInfo;
    }
    //告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
    {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName("md5");
        hashMatcher.setHashIterations(2);
        this.setCredentialsMatcher(hashMatcher);
    }

}
