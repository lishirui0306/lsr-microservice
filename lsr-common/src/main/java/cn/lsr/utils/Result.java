package cn.lsr.utils;


import java.io.Serializable;
import java.util.Map;

/**
 * = = 统一返回结果集
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static String SUCCESS_CODE = "200";

    /**
     * 返回状态码
     */
    private String status;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private T data;

    /**
     * 分页信息
     */
    private PageInfo page;

    /**
     * 其他内容
     */
    private Map<String, Object> ext;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public Result(){
        this.status = SUCCESS_CODE;
        this.message = "SUCCESS";
    }

    public Result(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(String status, String message, T data, Map<String, Object> ext) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.ext = ext;
    }

    public Result(String status, String message, T data, PageInfo pageInfo) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.page = pageInfo;
    }

    public Result(String status, String message, T data, Map<String, Object> ext, PageInfo pageInfo) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    public Result(String status, String message, T data, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        this.status = status;
        this.message = message;
        this.data = data;
        this.page = pageInfo;
    }

    public Result(String status, String message, T data, Map<String, Object> ext, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        this.status = status;
        this.message = message;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    //快速返回成功

    /**
     *  返回一个成功表示 不带数据
     * @param <T>
     * @return
     */
    public static <T>Result success(){
        return new Result<T>(SUCCESS_CODE,"请求成功",null);
    }

    /**
     *  返回数据 自动追加状态：请求成功
     * @param Result 数据
     * @param <T>
     * @return
     */
    public static <T>Result success(T Result){
        return new Result<T>(SUCCESS_CODE,"请求成功",Result);
    }

    /**
     *  返回数据+msg+自动追加状态码200
     * @param message   消息
     * @param Result    数据
     * @param <T>
     * @return
     */
    public static <T>Result success(String message, T Result){
        return new Result<T>(SUCCESS_CODE,message,Result);
    }

    /**
     *  返回数据+map+消息+状态码200
     * @param message   消息
     * @param Result    数据
     * @param extra     自定义map
     * @param <T>
     * @return
     */
    public static <T>Result success(String message, T Result, Map<String, Object> extra){
        return new Result<T>(SUCCESS_CODE,message,Result, extra);
    }

    /**
     *  返回数据+"请求成功"+分页参数pageinfo
     * @param Result    数据
     * @param total     总条数
     * @param pageNo    当前页数
     * @param pageSize  每页显示数据
     * @param <T>
     * @return
     */
    public static <T>Result success(T Result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,"请求成功",Result, pageInfo);
    }

    /**
     *  返回带附加 map  的数据集
     * @param Result    数据
     * @param extra     自定义map数据集
     * @param total     数据总条数
     * @param pageNo    当前页数
     * @param pageSize  每页显示多少条
     * @param <T>
     * @return
     */
    public static <T>Result success(T Result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,"请求成功",Result, extra,pageInfo);
    }

    /**
     *  返回自定义消息+数据+分页信息pageinfo
     * @param message   自定义消息
     * @param Result    数据
     * @param total     总条数
     * @param pageNo    当前页数
     * @param pageSize  每页多少条
     * @param <T>
     * @return
     */
    public static <T>Result success(String message, T Result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,message,Result,pageInfo);
    }

    /**
     *  返回"请求成功"+自定义消息+数据+自定义map+分页信息pageinfo
     * @param message   自定义消息
     * @param Result    数据
     * @param extra     自定义map
     * @param total     总条数
     * @param pageNo    当前页面
     * @param pageSize  每页多少条
     * @param <T>
     * @return
     */
    public static <T>Result success(String message, T Result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new Result<T>(SUCCESS_CODE,message,Result, extra,pageInfo);
    }

    //快速返回失败状态

    /**
     *  返回 500, "系统错误"
     * @param <T>
     * @return
     */
    public static <T>Result error(){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage());
    }

    /**
     *  返回 500, "系统错误" + 数据
     * @param Result 数据
     * @param <T>
     * @return
     */
    public static <T>Result error(T Result){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage(),Result);
    }

    /**
     *  返回 500 +自定义消息 + 数据
     * @param message   自定义消息
     * @param Result    数据
     * @param <T>
     * @return
     */
    public <T>Result error(String message, T Result){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,Result);
    }

    /**
     *  返回 500 +自定义消息+数据+自定义map
     * @param message   自定义消息  
     * @param Result    数据
     * @param extra     自定义map
     * @param <T>
     * @return
     */
    public <T>Result error(String message, T Result, Map<String, Object> extra){
        return new Result<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,Result, extra);
    }

    /**
     *  返回自定义 ErrorCode 类 (0，"xx")
     * @param errorCode  new ErrorCode
     * @param <T>
     * @return
     */
    public static <T>Result error(ErrorCode errorCode){
        return new Result<T>(errorCode.getCode(),errorCode.getMessage());
    }

    /**
     *  返回自定义ErrorCode + 数据
     * @param errorCode new ErrorCode
     * @param Result    数据
     * @param <T>
     * @return
     */
    public static <T>Result error(ErrorCode errorCode, T Result){
        return new Result<T>(errorCode.getCode(),errorCode.getMessage(),Result);
    }

    /**
     *  返回自定义ErrorCode + 自定义消息 + 数据
     * @param errorCode new ErrorCode
     * @param message   自定义消息
     * @param Result    数据
     * @param <T>
     * @return
     */
    public static <T>Result error(ErrorCode errorCode, String message, T Result){
        return new Result<T>(errorCode.getCode(),message,Result);
    }

    /**
     *  返回自定义ErrorCode + 自定义消息 + 数据 + 自定义map
     * @param errorCode new ErrorCode
     * @param message   自定义消息
     * @param Result    数据
     * @param extra     自定义map数据
     * @param <T>
     * @return
     */
    public static <T>Result error(ErrorCode errorCode, String message, T Result, Map<String, Object> extra){
        return new Result<T>(errorCode.getCode(),message,Result, extra);
    }

    //快速返回自定义状态码

    /**
     *  返回自定义 状态码(String) + 自定义消息
     * @param statusCode    自定义状态码
     * @param message       自定义消息
     * @param <T>
     * @return
     */
    public static <T>Result code(String statusCode, String message){
        return new Result<T>(statusCode,message);
    }

    /**
     *  返回自定义 状态码(String) + 自定义消息 + 数据
     * @param statusCode    自定义状态码
     * @param message       自定义消息
     * @param Result        数据
     * @param <T>
     * @return
     */
    public static <T>Result code(String statusCode, String message, T Result){
        return new Result<T>(statusCode,message,Result);
    }

    /**
     *  返回自定义 状态码(String) + 自定义消息 + 数据 + 自定义map
     * @param statusCode    自定义状态码
     * @param message       自定义消息
     * @param Result        数据
     * @param extra         自定义map
     * @param <T>
     * @return
     */
    public static <T>Result code(String statusCode, String message, T Result, Map<String, Object> extra){
        return new Result<T>(statusCode,message,Result, extra);
    }

    /**
     *  返回自定义 状态码(String) + 自定义消息 + 数据 + 分页数据pageinfo
     * @param statusCode    自定义状态码
     * @param message       自定义消息
     * @param Result        数据
     * @param total         总条数
     * @param pageNo        当前页数
     * @param pageSize      每页多少条数据
     * @param <T>
     * @return
     */
    public static <T>Result code(String statusCode, String message, T Result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new Result<T>(statusCode,message,Result, pageInfo);
    }

    /**
     *  返回自定义 状态码(String) + 自定义消息 + 数据 + 自定义map + 分页数据pageinfo
     * @param statusCode    自定义状态码
     * @param message       自定义消息
     * @param Result        数据
     * @param extra         自定义map
     * @param total         总条数
     * @param pageNo        当前页数
     * @param pageSize      每页多少条
     * @param <T>
     * @return
     */
    public static <T>Result code(String statusCode, String message, T Result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new Result<T>(statusCode,message,Result, extra,pageInfo);
    }

}
