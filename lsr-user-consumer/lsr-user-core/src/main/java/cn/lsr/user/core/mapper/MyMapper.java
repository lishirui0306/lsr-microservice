package cn.lsr.user.core.mapper;

import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * = =   通用mapper 注意:公用接口IBaseDao要单独的存在另一个包和普通dao分开，以免启动类的@MapperScan扫描到会报错
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> ,ExampleMapper<T> {
}
