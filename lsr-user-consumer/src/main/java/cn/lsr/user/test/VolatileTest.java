package cn.lsr.user.test;

/**
 * @Description:
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class VolatileTest {
    int x = 0 ;
    volatile boolean v = false;

    public void writer(){
        x = 42 ;
        v = true;
    }
    public void reader(){
        if (v = true){
            //   x = ? ; x = ?
        }
    }

}
