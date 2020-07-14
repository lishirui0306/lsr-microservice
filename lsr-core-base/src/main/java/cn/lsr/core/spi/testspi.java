package cn.lsr.core.spi;

/**
 * @Description:
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
public class testspi {
    /**
     * 获取指定id的实现
     */
    // SpiFactoriesLoader.getFactoryById(xx.class,"default");
    /**
     * 获取最大oder实现
     */
    // FactoriesLoader.getNewestFactory(xx.class);
    /**
     * META-INF ，下新建 spring.factories
     * cn.sunline.flow.gov.api.ModelManager=\
     * cn.sunline.flow.gov.service.DBModelManager,\
     * cn.sunline.flow.gov.service.NullModelManager
     * cn.sunline.flow.gov.api.ModelLoader=\
     * cn.sunline.flow.gov.service.ModelRegistCenterLoaderImp,\
     * cn.sunline.flow.gov.service.ModelDBLoaderImp
     */
    /**
     * 接口打注解@SPI
     */
    /**
     * 实现类打 @SPIMeta(id = 接口名.ID)
     *         @Order(2)
     * public static final String ID ="default";
     */
}
