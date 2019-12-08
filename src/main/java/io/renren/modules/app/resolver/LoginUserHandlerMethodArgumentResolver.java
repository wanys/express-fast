/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.resolver;

import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.interceptor.AuthorizationInterceptor;
import io.renren.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * 在一个web程序中，当一个HTTP请求进来时，会被容器处理进而转换成一个servlet请求。
 *
 * http请求所携带的数据，虽然是格式化的但是无类型；
 * 而java作为强类型语言，同时为了健壮性考虑，必然要有完善的类型约束。
 *
 * 当然，那么，将数据从servlet请求中转换到java中，一个很原始的方式是手动处理。
 * 幸好，Spring MVC通过以注解往函数添加额外信息的方式，使得上述的数据转换过程能够交由框架自动处理。
 * 从一个角度去看，Controller中的函数声明及注解定义了此HTTP请求的数据格式和类型，也即规定了对外部暴露的以http协议展现的接口。
 * 不过，有些时候内置注解无法满足需求的情况。这个时候，就需要自定义自己的注解以声明参数的格式。
 *
 * LoginUserHandlerMethodArgumentResolver 就是通过实现 HandlerMethodArgumentResolver接口
 * 而自定义的 ArgumentResolver (解析器)。
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        /*
            获取用户ID  这里通过我们自定义 拦截器 AuthorizationInterceptor 的 USER_KEY(我设置的是 openid字段)属性
            从 RequestAttributes(请求属性)中获取 用户信息
         */

        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }

        //获取用户信息
        UserEntity user = userService.queryByOpenid((String)object);

        return user;
    }
}
