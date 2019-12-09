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
     * 保存
     */
    @Login
    @ApiOperation("获取app用户订单")
    @GetMapping("order/list")
    public R ListOrder(@LoginUser UserEntity user){

        List<OrderEntity> orderList=orderService.queryListOrder(user.getUserId());

        return R.ok().put("orderList",orderList);
    }
}
