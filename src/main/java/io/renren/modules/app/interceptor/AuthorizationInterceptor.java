/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.interceptor;


import io.jsonwebtoken.Claims;
import io.renren.common.exception.RRException;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.app.annotation.Login;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 *
 * 定义一个Interceptor(拦截器) 非常简单方式,这里简单列举两种
 *     1、类要实现Spring 的HandlerInterceptor 接口
 *     2、类继承实现了HandlerInterceptor 接口的类，
 *     例如 已经提供的实现了HandlerInterceptor 接口的抽象类HandlerInterceptorAdapter
 *     注：这里我们用的就是第二种
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtils jwtUtils;

    public static final String USER_KEY = "openid";
    /*
        在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        /*
            Spring MVC应用启动时会搜集并分析每个Web控制器方法，
            从中提取对应的"<请求匹配条件,控制器方法>“映射关系，
            形成一个映射关系表保存在一个RequestMappingHandlerMapping bean中。
            然后在客户请求到达时，再使用RequestMappingHandlerMapping中的该映射关系表找到相应的控制器方法去处理该请求。
            在RequestMappingHandlerMapping中保存的每个”<请求匹配条件,控制器方法>"映射关系对儿中,
            "请求匹配条件"通过RequestMappingInfo包装和表示，而"控制器方法"则通过HandlerMethod来包装和表示。
         */
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }

        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtUtils.getHeader());
        }

        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new RRException(jwtUtils.getHeader() + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
            throw new RRException(jwtUtils.getHeader() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }

        //设置userId到request里，后续根据userId，获取用户信息

        //这里为了小程序 我修改为了 openid ,拦截验证通过，将用户信息放入 RequestAttribute
        // 后续根据openid，获取用户信息

        request.setAttribute(USER_KEY, claims.getSubject());

        return true;
    }

}
