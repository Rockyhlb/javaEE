package com.hlb.ioc.config;

import com.hlb.ioc.model.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.config
 * @CreateTime : 2024/3/14 12:49
 * @Description: 类注解：@Configuration + 方法注解：@Bean
 * @Author: code_hlb
 */
@Configuration
public class BeanConfig {
    // 方法注解@Bean 需要搭配其它五大注解一起使用
    @Bean({"user1","user2"})
    public UserInfo userInfo1() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName("zhangsan");
        userInfo.setAge(20);
        return userInfo;
    }

    @Bean("user3")
    public UserInfo userInfo2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(10);
        userInfo.setName("lisi");
        userInfo.setAge(30);
        return userInfo;
    }
}
