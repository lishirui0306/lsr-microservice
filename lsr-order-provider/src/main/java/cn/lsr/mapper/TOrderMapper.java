package cn.lsr.mapper;

import cn.lsr.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);

    TOrder testById(Integer id);
}