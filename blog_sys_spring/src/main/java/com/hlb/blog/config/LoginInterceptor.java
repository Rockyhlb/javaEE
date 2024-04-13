package com.hlb.blog.config;

import com.hlb.blog.constants.Constant;
import com.hlb.blog.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.config
 * @CreateTime : 2024/4/13 13:30
 * @Description: LoginInterceptor
 * @Author: code_hlb
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1、从请求头中获取token
        String token = request.getHeader(Constant.USER_TOKEN_HEADER);
        log.info("从header中获取到token:{}", token);
        // 2、校验token
        if (token != null && JWTUtils.checkToken(token)) {
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
