package com.mehow.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * login 拦截器，防止不经登陆直接访问
 * @Author: xuhui
 * @Date: 2020/8/4 17:28
 */
@Component
public class LoginInterceptor  extends HandlerInterceptorAdapter {
    /**
     * 登陆之前进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("------LoginInterceptor------拦截器执行-->>"+request.getSession().getAttribute("user") );
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
