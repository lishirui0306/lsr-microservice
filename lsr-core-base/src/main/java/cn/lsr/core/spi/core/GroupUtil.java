package cn.lsr.core.spi.core;

import cn.lsr.core.spi.annotation.Groups;

import java.util.*;

/**
 * @Description: spi分组工具类
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
public class GroupUtil {
    private GroupUtil(){}

    public static <T> List<T> filter(List<T> collections, String ...groups) {
        if (groups == null || groups.length == 0)
            return collections;

        if (collections == null)
            return Collections.emptyList();

        Set<String> groupFilters = new HashSet<>();
        Collections.addAll(groupFilters, groups);

        List<T> ret = new ArrayList<>();
        for (T obj : collections) {
            Groups groupsAnnotation = obj.getClass().getAnnotation(Groups.class);

            if (groupsAnnotation != null && groupsAnnotation.value() != null) {
                for (String annotation : groupsAnnotation.value()) {
                    if (groupFilters.contains(annotation)) {
                        ret.add(obj);
                        break;
                    }
                }
            }
        }

        return ret;
    }
}
