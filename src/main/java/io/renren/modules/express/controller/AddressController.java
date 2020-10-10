package io.renren.modules.express.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
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
    @Login
    @GetMapping("address/list")
   // @RequiresPermissions("express:address:list")
    public R list(@RequestParam Map<String, Object> params ,@LoginUser UserEntity user ){
        params.put("userId",user.getUserId().toString());
        PageUtils page = addressService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 保存
     */
    @Login
    @RequestMapping("address/save")
  //  @RequiresPermissions("express:address:save")
    public R save(@RequestBody AddressEntity address,@LoginUser UserEntity user ){
        address.setUserId(user.getUserId());
		addressService.save(address);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("address/update")
  //  @RequiresPermissions("express:address:update")
    public R update(@RequestBody AddressEntity address){

		addressService.updateById(address);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("address/delete")
   // @RequiresPermissions("express:address:delete")
    public R delete(@RequestBody AddressEntity address){
		addressService.removeById(address.getId());

        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("info/{userId}")
    // @RequiresPermissions("express:address:info")
    public R info(@PathVariable("userId") Long userId){
        AddressEntity address = addressService.getById(userId);

        return R.ok().put("address", address);
    }

}
