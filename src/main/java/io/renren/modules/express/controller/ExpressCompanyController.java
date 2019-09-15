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

import io.renren.modules.express.entity.ExpressCompanyEntity;
import io.renren.modules.express.service.ExpressCompanyService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 快递公司表
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@RestController
@RequestMapping("express/expresscompany")
public class ExpressCompanyController {
    @Autowired
    private ExpressCompanyService expressCompanyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("express:expresscompany:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = expressCompanyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{expressId}")
    @RequiresPermissions("express:expresscompany:info")
    public R info(@PathVariable("expressId") Integer expressId){
		ExpressCompanyEntity expressCompany = expressCompanyService.getById(expressId);

        return R.ok().put("expressCompany", expressCompany);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("express:expresscompany:save")
    public R save(@RequestBody ExpressCompanyEntity expressCompany){
		expressCompanyService.save(expressCompany);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("express:expresscompany:update")
    public R update(@RequestBody ExpressCompanyEntity expressCompany){
		expressCompanyService.updateById(expressCompany);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("express:expresscompany:delete")
    public R delete(@RequestBody Integer[] expressIds){
		expressCompanyService.removeByIds(Arrays.asList(expressIds));

        return R.ok();
    }

}
