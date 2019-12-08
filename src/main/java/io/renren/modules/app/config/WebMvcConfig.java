/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.config;

import io.renren.modules.app.interceptor.AuthorizationInterceptor;
import io.renren.modules.app.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * MVC配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;//自定义拦截器实例
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;//自定义 参数解析器实例

    /* 拦截器配置 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
            addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
                我们自定义的 AuthorizationInterceptor类

            addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
            excludePathPatterns：用于设置不需要拦截的过滤规则
            拦截器主要用途：进行用户登录状态的拦截，日志的拦截等。
        */

        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/app/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}