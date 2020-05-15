package cn.lsr.rpc.factory;

import cn.lsr.rpc.provider.RpcClientDynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @Description: 代理工厂
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
public class ProxyFactory {
    public static <T> T create(Class<T> interfaceClass) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[] {interfaceClass}, new RpcClientDynamicProxy<T>(interfaceClass));
    }
}
