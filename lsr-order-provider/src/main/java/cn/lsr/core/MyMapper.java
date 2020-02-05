package cn.lsr.core;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * @author Hacker_lsr@126.com
 * @version V1.0
 * @Description: 通用mapper
 **/
public interface MyMapper<T> extends Mapper<T> ,MySqlMapper<T> {

}
