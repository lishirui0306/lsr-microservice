package cn.lsr.mvc.dao;

import cn.lsr.mvc.annotation.LSRRepository;
import cn.lsr.mvc.annotation.LSRService;

/**
 * @Description:
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@LSRRepository("testDaoImp")
public class TestDaoImp implements TestDao{
    @Override
    public void test() {
        System.out.println("我是dao 的实现方法 ：test()");
    }
}
