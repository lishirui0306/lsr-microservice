package cn.lsr.controller;

import cn.lsr.entity.TOrder;
import cn.lsr.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@Api(tags="order消费者")
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/get/list")
    @ApiOperation(value = "获取所有的order",notes = "查询所有")
    public List<TOrder> findAll(){
        return orderService.selectAll();
    }

    @ApiOperation(value = "根据id获取对应的数据")
    public TOrder selectByPrimaryKey(@ApiParam(value = "id",name = "order的id",required = true) Integer id){
        return orderService.testById(id);
    }

}
