/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.utils.DatetimeUtil;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.app.entity.Template;
import io.renren.modules.app.entity.TemplateParam;
import io.renren.modules.app.utils.UrlUtil;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.app.utils.WXUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * APP登录授权
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
@Api("APP登录接口")
public class AppLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @Value("${AppID}")
    private String AppID;

    @Value("${AppSecret}")
    private String AppSecret;

    String openid;
    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        long userId = userService.login(form);

               //生成token
        String token = jwtUtils.generateToken(userId+"");

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return R.ok(map);
    }

    private JSONObject getUserWXLoginInfo(String wxCode) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", AppID);	//开发者设置中的appId
        requestUrlParam.put("secret", AppSecret);	//开发者设置中的appSecret
        requestUrlParam.put("js_code", wxCode);	//小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");	//默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    @GetMapping("loginByPower")
    @ApiOperation("忽略Token验证测试")
    public R login(@RequestParam("code") String wxCode,@RequestParam("userInfoDetail") String userInfoDetail){
        //请求微信api获取用户的openid和sessionKey
        JSONObject jsonObject = getUserWXLoginInfo(wxCode);
        if(jsonObject!=null&&!jsonObject.containsKey("openid")) {
            return R.error().put("no_power","未授权！");
        }
          openid = (String)jsonObject.get("openid");
        //String sessionKey = (String)jsonObject.get("session_key");

        JSONObject user_info_detail= JSONObject.parseObject(userInfoDetail);
        //String encryptedData=user_info_detail.getString("encryptedData");
       // String iv=user_info_detail.getString("iv");
        String user_info=user_info_detail.getString("userInfo");
        JSONObject userinfo= JSONObject.parseObject(user_info);
        String userName=userinfo.getString("nickName");


        //JSONObject resolved=WXUtils.getUserInfo(encryptedData,sessionKey,iv);

        //通过openid查询数据库是否有此用户
        UserEntity oldUser = userService.queryByOpenid(openid);
        if(oldUser!=null) {//用户已存在
            if(oldUser.getPhone()==null) {
                oldUser.setPhone("");
            }else {
                oldUser.setPhone(oldUser.getPhone());
            }
            //redisTemplate.opsForValue().set(openid,sessionKey,1000, TimeUnit.SECONDS);//存到redis中,设置失效时间
            //生成token

            String token = jwtUtils.generateToken(openid);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("expire", jwtUtils.getExpire());

            return R.ok(map);
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userName);
        newUser.setOpenid(openid);
        newUser.setRole("ExpressMan");

        if(newUser.getPhone()==null) {
            newUser.setPhone("");
        }else {
            newUser.setPhone(newUser.getPhone());
        }

        userService.save(newUser);

        //RedisUtils.
        //redisTemplate.opsForValue().set(openid,sessionKey,1000, TimeUnit.SECONDS);//存到redis中,设置失效时间

        //生成token
        String token = jwtUtils.generateToken(openid);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return R.ok(map);
    }


    //获取订阅消息推送的token
    private JSONObject getUserWXOAthToken( ) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", AppID);	//开发者设置中的appId
        requestUrlParam.put("secret", AppSecret);	//开发者设置中的appSecret
        requestUrlParam.put("grant_type", "client_credential");	//默认参数
        //获取小程序全局唯一后台接口调用凭据（access_token）。调用绝大多数后台接口时都需使用 access_token，开发者需要进行妥善保存。
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }





    @GetMapping("GetDYMsg")
    @ApiOperation("获取订阅消息推送")
    public R  msg() {
        JSONObject jsonObject = getUserWXOAthToken();
        String access_token = (String) jsonObject.get("access_token");
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+access_token;
        Template template = new Template();
        //   template.setTemplate_id("jeg6W3W53iJLWXb_N85pQ6xHOBN-6Pv_UEld-kJb3rU");
        //    template.setTouser("openid");
        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("thing1", "shuju"));
        paras.add(new TemplateParam("thing8", "学习"));
        paras.add(new TemplateParam("thing10", "xuexi"));
        template.setTemplateParamList(paras);
       /* Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("access_token", access_token);	//开发者设置中的access_token
        requestUrlParam.put("touser", openid);	//开发者openid
        requestUrlParam.put("template_id", "jeg6W3W53iJLWXb_N85pQ6xHOBN-6Pv_UEld-kJb3rU");	//模板id
        requestUrlParam.put("data", template);*/


        Map<String,String> requestUrlParam = new HashMap<String,String>();
     //   requestUrlParam.put("access_token", access_token);	//开发者设置中的access_token
        requestUrlParam.put("touser", openid);	//开发者openid
        requestUrlParam.put("template_id", "jeg6W3W53iJLWXb_N85pQ6xHOBN-6Pv_UEld-kJb3rU");	//模板id
        requestUrlParam.put("data", JSON.toJSONString(template));
        JSONObject jsonObject1 = JSON.parseObject(UrlUtil.sendPost(requestUrl,requestUrlParam));
//        if (jsonObject1 != null) {
//            System.out.println(jsonObject1);
//            String errorCode = jsonObject1.getString("errcode");
//            String errorMessage = jsonObject1.getString("errmsg");
//            return R.ok();
//        }
        return R.ok(jsonObject1);
//        if (errorCode.equals('0')) {
//            System.out.println("Send Success");
//            return "success";
//        } else {
//            System.out.println("订阅消息发送失败:" + errorCode + "," + errorMessage);
//            return "失败";
//        }
    }




}
