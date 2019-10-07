package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.service.OrderService;
import io.renren.modules.express.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app")
@Api("APP任务接口")
public class AppOrderController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @Login
    @ApiOperation("任务列表")
    @GetMapping("task/list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = taskService.queryPageWeChat(params);

        return R.ok().put("page", page);
    }

    /**
     * 保存
     */
    @Login
    @ApiOperation("任务列表")
    @PostMapping("order/save")
    public R save(@RequestBody OrderEntity order,@LoginUser UserEntity user){

        orderService.WeChatSaveOrder(order,user);

        return R.ok();
    }
}
