package com.hlb.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.config
 * @CreateTime : 2024/4/13 13:44
 * @Description: WebConfigure
 * @Author: code_hlb
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    private final List<String> excludes = Arrays.asList("/**/*.html", "/blog-editormd/**", "/css/*", "/js/*", "/images/*", "/user/login","/user/register","/user/updateUser");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludes);
    }
}
