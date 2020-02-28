package cn.lsr.core.config.druid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description: 动态数据源切换
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    // 当前线程数据源
    private static final ThreadLocal<DataSourceType> dataSourceContext = new ThreadLocal<>();
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("数据源：{}",getDataSource());
        return getDataSource();
    }

    /**
     * 获取当前数据源
     * @return
     */
    public DataSourceType getDataSource(){
        return dataSourceContext.get();
    }

    /**
     * 设置数据源
     * @param dataSourceType
     */
    public static void setDataSource(DataSourceType dataSourceType){
        dataSourceContext.set(dataSourceType);
    }

    /**
     * 删除数据源
     */
    public static void clearDataSource(){
        dataSourceContext.remove();
    }
}
