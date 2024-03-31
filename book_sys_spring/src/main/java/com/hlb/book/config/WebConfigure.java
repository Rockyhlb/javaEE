package com.hlb.book.config;

import com.hlb.book.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.config
 * @CreateTime : 2024/3/27 19:00
 * @Description: 注册拦截器（声明工作范围）
 * @Author: code_hlb
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {
    // 取出注入的自定义拦截器对象
    @Autowired
    private LoginInterceptor loginInterceptor;

    private final List<String> excludePaths = Arrays.asList("/user/**", "/**/user_regist.html", "/**/user_update.html", "/**/login.html", "/css/**", "/js/**", "/image/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器对象
        // "/**"：表示对所有的路径都生效，拦截所有请求
        // "/*": 表示一级路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePaths); // 排除必要的网页路径
    }
}
