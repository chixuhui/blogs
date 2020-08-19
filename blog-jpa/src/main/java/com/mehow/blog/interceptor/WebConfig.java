package com.mehow.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置,那些地址是需要拦截的在这里配置  请注意，只拦截admin下面的地址
 * @Author: xuhui
 * @Date: 2020/8/4 17:30
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("----------WebMvcConfigurer---过滤拦截器-------->>>");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")//路径匹配
                .excludePathPatterns("/admin")//排除路径
                .excludePathPatterns("/admin/login");//排除路径
    }
}
