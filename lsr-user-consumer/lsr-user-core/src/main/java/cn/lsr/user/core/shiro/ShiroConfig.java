package cn.lsr.user.core.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * = = shiro 配置类
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Configuration
public class ShiroConfig {
    //注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
    @Bean
    public Realm realm() {
        return new AuthRealm();
    }
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * 过滤器：
     * anon	                org.apache.shiro.web.filter.authc.AnonymousFilter
     * authc	            org.apache.shiro.web.filter.authc.FormAuthenticationFilter
     * authcBasic	        org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
     * logout	            org.apache.shiro.web.filter.authc.LogoutFilter
     * noSessionCreation	org.apache.shiro.web.filter.session.NoSessionCreationFilter
     * perms	            org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
     * port	                org.apache.shiro.web.filter.authz.PortFilter
     * rest	                org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
     * roles	            org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
     * ssl  	            org.apache.shiro.web.filter.authz.SslFilter
     * user	                org.apache.shiro.web.filter.authc.UserFilter
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        // 因为DefaultShiroFilterChainDefinition 没有配置无权限拦截，所有要在yml 配置无权限的跳转
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //chain.addPathDefinition("/js**", "anon");//释放静态资源
        //chain.addPathDefinition("/css**", "anon");//释放静态资源
        //chain.addPathDefinition("/founts/**", "anon");//释放静态资源
        //chain.addPathDefinition("/images**", "anon");//释放静态资源
        chain.addPathDefinition("/static/**", "anon");//释放静态资源
        chain.addPathDefinition("/403", "anon");//
        chain.addPathDefinition("/getCode", "anon");//释放静态资源
        chain.addPathDefinition("/tologin", "anon");//可以匿名访问
        chain.addPathDefinition("/t4/user", "user");//已登录或“记住我”的用户可以访问
        chain.addPathDefinition("/admin/**", "authc,roles[admin]");//需要 admin 角色
        chain.addPathDefinition("/edit/**", "authc,perms[admin]");//需要admin权限
        chain.addPathDefinition("/find/**", "authc,perms[select]");//需要select权限
        // shiro 提供的登出过滤器，访问指定的请求，就会执行登录，默认跳转路径是"/"，或者是"shiro.loginUrl"配置的内容
        // 由于application-shiro.yml中配置了 shiro:loginUrl: /page/401，返回会返回对应的json内容
        // 可以结合/user/login和/t1/js接口来测试这个/t4/logout接口是否有效
        chain.addPathDefinition("/logout", "anon,logout");
        //其它路径均需要登录
        chain.addPathDefinition("/**", "authc");
        return chain;
    }


}
