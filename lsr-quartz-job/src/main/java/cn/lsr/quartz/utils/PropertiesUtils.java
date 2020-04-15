package cn.lsr.quartz.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Description: 配置文件加载工具
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class PropertiesUtils {
    public static Properties properties;

    /**
     * 读取配置文件
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Properties readProperties(String path) throws FileNotFoundException, IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);
        InputStream inputStream =classPathResource.getInputStream();
        properties = new Properties();
        properties.load(new InputStreamReader(inputStream, "UTF-8"));
        return properties;
    }
}
