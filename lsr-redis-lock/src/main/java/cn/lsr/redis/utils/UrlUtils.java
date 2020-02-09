package cn.lsr.redis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @Description: 组装url
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
public class UrlUtils {
    private static final Logger log = LoggerFactory.getLogger(UrlUtils.class);
    public static String remouldUrl(String url,String id ,String value){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(url);
        stringBuffer.append("?id="+id+"&value="+value+"");
        log.info("调用的url："+stringBuffer.toString());
        return stringBuffer.toString();
    }

}
