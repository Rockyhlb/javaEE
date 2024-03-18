package com.hlb.ioc.config;

import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.config
 * @CreateTime : 2024/3/14 12:29
 * @Description: 类注解：@Configuration
 * @Author: code_hlb
 */
@Configuration
public class UserConfig {
    public void welcome() {
        System.out.println("hello,UserConfig!");
    }
}
