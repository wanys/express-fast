package io.renren.modules.express.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 任务表
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@RestController
@RequestMapping("express/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("express:task:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = taskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{taskId}")
    @RequiresPermissions("express:task:info")
    public R info(@PathVariable("taskId") String taskId){
		TaskEntity task = taskService.getById(taskId);

        return R.ok().put("task", task);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("express:task:save")
    public R save(@RequestBody TaskEntity task){
		taskService.save(task);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("express:task:update")
    public R update(@RequestBody TaskEntity task){
		taskService.updateById(task);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("express:task:delete")
    public R delete(@RequestBody String[] taskIds){
		taskService.removeByIds(Arrays.asList(taskIds));

        return R.ok();
    }

}
