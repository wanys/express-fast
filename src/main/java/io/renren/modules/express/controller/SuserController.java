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

import io.renren.modules.express.entity.SuserEntity;
import io.renren.modules.express.service.SuserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户表
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@RestController
@RequestMapping("express/suser")
public class SuserController {
    @Autowired
    private SuserService suserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("express:suser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = suserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("express:suser:info")
    public R info(@PathVariable("userId") Integer userId){
		SuserEntity suser = suserService.getById(userId);

        return R.ok().put("suser", suser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("express:suser:save")
    public R save(@RequestBody SuserEntity suser){
		suserService.save(suser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("express:suser:update")
    public R update(@RequestBody SuserEntity suser){
		suserService.updateById(suser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("express:suser:delete")
    public R delete(@RequestBody Integer[] userIds){
		suserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
