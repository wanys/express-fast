package io.renren.modules.express.controller;

import io.renren.common.utils.R;
import io.renren.modules.express.entity.TaskCharBar;
import io.renren.modules.express.entity.OrderCharLine;
import io.renren.modules.express.service.OrderService;
import io.renren.modules.express.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 工作量统计
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@RestController
@RequestMapping("express/echars")
public class EchartsController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private OrderService orderService;
    /**
     * 列表
     */
    @RequestMapping("/line")
    public R Line(@RequestParam Map<String, Object> params){

        OrderCharLine orderCharLine = orderService.queryPageEcharsLine();

        return R.ok().put("taskLine", orderCharLine);
    }


    /**
     * 列表
     */
    @RequestMapping("/bar")
    public R Bar(@RequestParam Map<String, Object> params){

        TaskCharBar taskCharBar= taskService.queryPageEcharsBar();

        return R.ok().put("taskBar",taskCharBar);
    }
    /**
     * 列表
     */
    @RequestMapping("/pie")
    public R Pie(@RequestParam Map<String, Object> params){

        TaskCharBar taskCharBar= taskService.queryPageEcharsBar();

        return R.ok().put("taskPie",taskCharBar);
    }

}
