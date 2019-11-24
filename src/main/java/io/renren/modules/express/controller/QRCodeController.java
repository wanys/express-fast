package io.renren.modules.express.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("express/qrcode")
public class QRCodeController {

    @RequestMapping("/print")
    @RequiresPermissions("express:qrcode:print")
    public R list(@RequestParam Map<String, Object> params){

        String ww= (String) params.get("key");
        //PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", 111);
    }
}
