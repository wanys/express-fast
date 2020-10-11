package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app")
@Api("APP任务接口")
public class AppOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 保存
     */
    @Login
    @ApiOperation("保存订单")
    @PostMapping("order/save")
    public R save(@RequestBody OrderEntity order,@LoginUser UserEntity user){

        orderService.WeChatSaveOrder(order,user);

        return R.ok();
    }

    /**
     * 获取app用户订单
     */
    @Login
    @ApiOperation("获取app用户订单")
    @GetMapping("order/list")
    public R ListOrder(@LoginUser UserEntity user){

        List<OrderEntity> orderList=orderService.queryListOrder(user.getUserId());

        return R.ok().put("orderList",orderList);
    }

    /**
     * 获取用户取件订单
     */
    @Login
    @ApiOperation("获取用户取件订单")
    @GetMapping("order/getOrder")
    public R getOrder(@LoginUser UserEntity user){
        List<OrderEntity> getOrderList=orderService.queryListGetOrder(user.getUserId());
        return R.ok().put("orderList",getOrderList);
    }


    /*
    * 入库操作
    * */
    @Login
    @PostMapping("/order/ruku/{transportNo}")
    public R ruku(@PathVariable("transportNo") String transportNo,@LoginUser UserEntity userEntity){
        orderService.rukuOrder(transportNo,userEntity);
        return R.ok();
    }


    /**
     * 用户取件操作
     */
    @Login
    @ApiOperation("用户取件")
    @PostMapping("order/qujian/{transportNo}")
    public R qujian(@PathVariable("transportNo") String transportNo,@LoginUser UserEntity userEntity){
        orderService.qujian(transportNo,userEntity);
        return R.ok();
    }
}
