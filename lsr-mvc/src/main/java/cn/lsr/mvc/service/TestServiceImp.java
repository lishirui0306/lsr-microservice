package cn.lsr.mvc.service;

import cn.lsr.mvc.annotation.LSRQualifier;
import cn.lsr.mvc.annotation.LSRService;
import cn.lsr.mvc.dao.TestDao;

/**
 * @Description:
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@LSRService("testServiceImp")
public class TestServiceImp implements TestService{
    @LSRQualifier("testDaoImp")
    private TestDao dao ;
    @Override
    public void test() {
      dao.test();
    }
}
