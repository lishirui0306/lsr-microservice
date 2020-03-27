package cn.lsr.core.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 请求日志切面
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class HttpLogAspect {
    public static Logger log = LoggerFactory.getLogger(HttpLogAspect.class);
    @Pointcut("execution(public * cn.lsr.*.controller.*(..))")
    public void log(){}
    @Before("log()")
    public void beforLog(JoinPoint joinPoint){
        //获得请求中的属性
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获得请求
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 记录请求
        // 1.url
        // 2.请求方式
        // 3.客户端的IP
        log.info("url={}", request.getRequestURL(),"method={}", request.getMethod(),"IP={}", request.getRemoteAddr());
        // 4.请求执行的controller中的那个方法
        /*
         * joinPoint.getSignature().getDeclaringTypeName() --- 获得类名
         * joinPoint.getSignature().getName() ---- 获得方法名
         */
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName());
        //5.请求参数......
        log.info("args={}", joinPoint.getArgs());
        log.info("请求进入controller");
    }
    @After("log()")
    public void afterLog() {
        log.info("执行方法后响应");
    }
    /**
     * 获得放回的结果
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void afterReturningLog(Object object) {
        log.info("response={}", object);
        System.out.println("返回结果之后");
    }
}
