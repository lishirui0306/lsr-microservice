package cn.lsr.core.spi.core;

import cn.lsr.core.spi.annotation.SPI;
import cn.lsr.core.spi.annotation.SPIIMP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: spi工厂
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
public class SpiFactoriesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpiFactoriesLoader.class);

    private static final  Map<Class<?>, Map<String, Object>> cache = new ConcurrentHashMap<>();

    private static final Map<String, Object> NULL_MAP = Collections.emptyMap();

    public SpiFactoriesLoader() {
    }

    private static <T> Map<String, T> getFactories(Class<T> factoryClass) {
        Map<String, T> factoryInstances = (Map<String, T>) cache.get(factoryClass);

        if (factoryInstances == null) {
            loadFactories(factoryClass);
            factoryInstances = (Map<String, T>) cache.get(factoryClass);
        }
        return factoryInstances;
    }


    private static void loadFactories(Class<?> factoryClass) {
        //调用SpringFactoriesLoader返回已经排好序的工厂实例列表
        List<?> factories = SpringFactoriesLoader.loadFactories(factoryClass, null);

        if (factories == null) {
            cache.put(factoryClass, NULL_MAP);
            return;
        }
        Map<String, Object> factoryInstances = new LinkedHashMap<>();
        for (Object factory : factories) {
            //获取扩展点的spi名称
            String spiName = getSpiName(factory.getClass());
            Object old = factoryInstances.get(spiName);
            if (old != null) {
                LOGGER.warn("SPI id has already exist, new class's order >= old class's order, ovrride.[new class = [{}], old class = [{}]]",
                        factory.getClass().getName(),
                        old.getClass().getName());
            }
            factoryInstances.put(spiName, factory);
        }
        cache.putIfAbsent(factoryClass, factoryInstances);
    }

    /**
     * 获取扩展点的spi名称，
     * @param clazz
     * @return
     *
     * 若使用SPIMeta显示指定spi名称，则直接返回该spi名称。若未配置，则使用类类路径作为spi名称
     */
    private static String getSpiName(Class<?> clazz) {
        SPIIMP spiMeta = clazz.getAnnotation(SPIIMP.class);
        //若未配置注解，则默认使用完整的类名作为key值
        if (spiMeta == null) {
            return clazz.getSimpleName();
        }

        return (!"".equals(spiMeta.id())) ? spiMeta.id() : clazz.getSimpleName();
    }

    /**
     * 根据接口上定义的默认实现获取对应的实例
     *
     * @return 若没有定义默认实现则返回NULL
     */
    public static <T> T getDefaultFactory(Class<T> factoryClass) {
        SPI spi = factoryClass.getAnnotation(SPI.class);
        if (spi == null)
            return null;

        String id = spi.defaultId();

        if (id != null) {
            Map<String, T> factoryInstances = getFactories(factoryClass);
            return factoryInstances.get(id);
        }

        return null;
    }

    /**
     * 获取ORDER最大的实现对应的实例
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getNewestFactory(Class<T> factoryClass) {

        Map<String, T> factoryInstances = getFactories(factoryClass);

        if (factoryInstances.isEmpty()) {
            return null;
        } else {

            return (T) factoryInstances.values().toArray()[factoryInstances.size() - 1];
        }
    }

    /**
     * 获取ORDER最小的实现对应的实例
     *
     * @return
     */
    public static <T> T getOldestFactory(Class<T> factoryClass) {
        Map<String, T> factoryInstances = getFactories(factoryClass);

        if (factoryInstances.isEmpty()) {
            return null;
        } else {
            return factoryInstances.values().iterator().next();
        }
    }

    /**
     * 根据指定ID获取对应的实例
     *
     * @param id SPI实现上定义的ID
     * @return 若没有对应实现则返回NULL
     */
    public static <T> T getFactoryById(Class<T> factoryClass, String id) {
        Map<String, T> factoryInstances = getFactories(factoryClass);

        if (factoryInstances.isEmpty()) {
            return null;
        } else {
            return factoryInstances.get(id);
        }
    }
    /**
     * 获取全部实现实例列表
     *
     * @return 若没有任何实现则返回空列表
     */
    public static <T> List<T> getAllFactories(Class<T> factoryClass) {
        Map<String, T> factoryInstances = getFactories(factoryClass);
        if (factoryInstances.size() == 0) {
            return Collections.emptyList();
        }

        List<T> ret = new ArrayList<>();

        for (Map.Entry<String, T> entry : factoryInstances.entrySet()) {
            ret.add(entry.getValue());
        }

        return ret;
    }

    /**
     * 按GROUP获取实现实例列表
     *
     * @param groups
     *            实现的分组名
     * @return 若没有符合条件的实现则返回空列表
     */
    public static <T> List<T> getFactoriesByGroup(Class<T> factoryClass, String ...groups) {
        Map<String, T> factoryInstances = getFactories(factoryClass);
        if (factoryInstances.size() == 0) {
            return Collections.emptyList();
        }

        return GroupUtil.filter(new ArrayList<>(factoryInstances.values()), groups);
    }
}
