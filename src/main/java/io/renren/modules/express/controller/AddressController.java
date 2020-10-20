package io.renren.modules.express.controller;


import java.util.Arrays;
import java.util.Map;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.express.entity.AddressEntity;
import io.renren.modules.express.service.AddressService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-12-26 20:27:45
 */
@RestController
@RequestMapping("express/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 列表
     */

    @GetMapping("/list")
    @RequiresPermissions("express:address:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addressService.queryPagepc(params);

        return R.ok().put("page", page);
    }


    /**
     * 保存
     */

    @RequestMapping("/save")
   @RequiresPermissions("express:address:save")
    public R save(@RequestBody AddressEntity comment){
        addressService.save(comment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("express:address:update")
    public R update(@RequestBody AddressEntity address){

		addressService.updateById(address);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("express:address:delete")
    public R delete(@RequestBody Integer[]  addrId){
		addressService.removeByIds(Arrays.asList(addrId));

        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{addrId}")
     @RequiresPermissions("express:address:info")
    public R info(@PathVariable("addrId") Integer addrid){
        AddressEntity address = addressService.getById(addrid);

        return R.ok().put("address", address);
    }

}
