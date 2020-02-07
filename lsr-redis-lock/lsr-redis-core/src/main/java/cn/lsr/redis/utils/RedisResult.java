package cn.lsr.redis.utils;

/**
 * @Description: redis锁返回对象
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
public class RedisResult {
    public static Integer SUCCESS = 200;
    public static Integer ERROR = 400;
    private boolean b;
    private Integer status;
    private String messages;

    public static Integer getSUCCESS() {
        return SUCCESS;
    }

    public static void setSUCCESS(Integer SUCCESS) {
        RedisResult.SUCCESS = SUCCESS;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public static void setERROR(Integer ERROR) {
        RedisResult.ERROR = ERROR;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public RedisResult() {
    }

    public RedisResult(boolean b, Integer status, String messages) {
        this.b = b;
        this.status = status;
        this.messages = messages;
    }

    public static  RedisResult success(boolean b){
        return new RedisResult(b,SUCCESS,"获取锁成功！");
    }

    public static RedisResult error(boolean b){
        return  new RedisResult(b,ERROR,"获得锁失败！");
    }
    public static   RedisResult success(boolean b,String s ){
        return new RedisResult(b,SUCCESS,s);
    }

    public static RedisResult error(boolean b,String s){
        return  new RedisResult(b,ERROR,s);
    }
}
