package cn.lsr.rpc.asyn;

import cn.lsr.rpc.protocol.RpcResponse;

/**
 * @Description: 模拟异步调用 因为Netty是异步框架，所有的返回都是基于Future和Callback机制的，我们这里自定义Future来实现客户端"异步调用"
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
public class DefaultFuture {
    private RpcResponse rpcResponse;
    //实际上用了wait和notify机制，同时使用一个boolean变量做辅助
    private volatile boolean isSucceed = false;
    private final Object object = new Object();

    public RpcResponse getRpcResponse(int timeout) {
        synchronized (object) {
            while (!isSucceed) {
                try {
                    object.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return rpcResponse;
        }
    }

    public void setResponse(RpcResponse response) {
        if (isSucceed) {
            return;
        }
        synchronized (object) {
            this.rpcResponse = response;
            this.isSucceed = true;
            object.notify();
        }
    }
}
