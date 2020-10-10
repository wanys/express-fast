package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app")
@Api("APP任务接口")
public class AppTaskController {

    @Autowired
    private TaskService taskService;
    /**
     * 列表
     */
    @Login
    @ApiOperation("任务列表")
    @GetMapping("task/list")
    public R list(@RequestParam Map<String, Object> params,@LoginUser UserEntity user){

        params.put("taskReceiverId",user.getUserId().toString());
        PageUtils page = taskService.queryPageWeChat(params);

        return R.ok().put("page", page);
    }

    /**
     * 绑定运单，揽件
     */
    @Login
    @ApiOperation("绑定运单")
    @PostMapping("task/bindWayBill")
    public R bindWayBill(@RequestBody TaskEntity taskEntity,@LoginUser UserEntity userEntity){

        Boolean bool= taskService.WeChatBindWayBill(taskEntity,userEntity);
        if(bool==true){
            return R.ok();
        }
        return R.error("快递单绑定失败");
    }


    /**
     * 快递员派件到驿站
     */
    @Login
    @ApiOperation("快递员派件到驿站")
    @PostMapping("task/paijianToDak/{transportNo}")
    public R paiJianToDak(@PathVariable("transportNo") String transportNo,@LoginUser UserEntity userEntity){
        taskService.paijianToDak(transportNo,userEntity);
        return R.ok();
    }

    /**
     * 快递员派件到用户
     */
    @Login
    @ApiOperation("快递员派件到用户")
    @PostMapping("task/paijianToUser/{transportNo}")
    public R paiJianToUser(@PathVariable("transportNo") String transportNo,@LoginUser UserEntity userEntity){
        taskService.paijianToUser(transportNo,userEntity);
        return R.ok();
    }
}
