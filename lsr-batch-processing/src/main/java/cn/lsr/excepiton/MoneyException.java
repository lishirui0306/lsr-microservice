package cn.lsr.excepiton;

/**
 * @Description: 资金异常
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class MoneyException extends Exception{
    public MoneyException(){}

    public MoneyException(String message) {
        super(message);
    }

    public MoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyException(Throwable cause) {
        super(cause);
    }
}
