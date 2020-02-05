package cn.lsr.service.imp;

import cn.lsr.entity.TOrder;
import cn.lsr.mapper.OrderDao;
import cn.lsr.mapper.TOrderMapper;
import cn.lsr.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class OrderServiceImp implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private TOrderMapper tOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {

        return orderDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TOrder record) {
        return orderDao.insert(record);
    }

    @Override
    public TOrder selectByPrimaryKey(Integer id) {
        return tOrderMapper.testById(id);
    }

    @Override
    public List<TOrder> selectAll() {
        return tOrderMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TOrder record) {
        return orderDao.updateByPrimaryKey(record);
    }

    @Override
    public TOrder testById(Integer id) {
        return tOrderMapper.testById(id);
    }
}
