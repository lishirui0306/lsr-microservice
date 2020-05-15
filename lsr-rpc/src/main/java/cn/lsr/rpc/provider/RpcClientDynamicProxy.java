package cn.lsr.rpc.provider;

import cn.lsr.rpc.NettyClient;
import cn.lsr.rpc.protocol.RpcRequest;
import cn.lsr.rpc.protocol.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 客户端动态代理
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
public class RpcClientDynamicProxy<T> implements InvocationHandler {
    private Logger log = LoggerFactory.getLogger(RpcClientDynamicProxy.class);
    private Class<T> clazz;
    public RpcClientDynamicProxy(Class<T> clazz) throws Exception {
        this.clazz = clazz;
    }

    /**
     * 在invoke方法中封装请求对象，构建NettyClient对象，并开启客户端，发送请求消息
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        String requestId = UUID.randomUUID().toString();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        request.setRequestId(requestId);
        request.setClassName(className);
        request.setMethodName(methodName);
        request.setParameterTypes(parameterTypes);
        request.setParameters(args);
        log.info("请求内容: {}",request);
        //开启Netty 客户端，直连
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8080);
        log.info("开始连接服务端：{}",new Date());
        nettyClient.connect();
        RpcResponse send = nettyClient.send(request);
        log.info("请求调用返回结果：{}", send.getResult());
        return send.getResult();
    }
}
