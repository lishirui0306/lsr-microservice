package cn.lsr.service;

import cn.lsr.entity.TOrder;
import java.util.List;

public interface OrderService {

    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    /**
     * 获取所有的信息order
     * @return
     */
    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);

    /**
     * 根据id获取order信息
     * @param id 指定的id
     * @return
     */
    TOrder testById(Integer id);
}
