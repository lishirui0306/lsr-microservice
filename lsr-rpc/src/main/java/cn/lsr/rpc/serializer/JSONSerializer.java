package cn.lsr.rpc.serializer;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @Description: json方式序列化
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
public class JSONSerializer implements Serializer{
    @Override
    public byte[] serialize(Object object) throws IOException {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        return JSON.parseObject(bytes,clazz);
    }
}
