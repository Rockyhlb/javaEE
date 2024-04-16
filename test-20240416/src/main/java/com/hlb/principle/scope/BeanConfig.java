package com.hlb.principle.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @BelongsProject: test-20240416
 * @BelongsPackage: com.hlb.principle
 * @CreateTime : 2024/4/16 18:10
 * @Description: TODO
 * @Author: code_hlb
 */
@Configuration
public class BeanConfig {
    @Bean
    public User user() {
        return new User("张三");
    }

    /**
     * 单例作用域
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public User singleUser() {
        return new User("张三");
    }

    /**
     * 原型作用域 (多例作用域)
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        return new User();
    }

    /**
     * 请求作用域
     */
    @Bean
    @RequestScope
    public User requestUser() {
        return new User();
    }

    /**
     * 会话作用域
     */
    @Bean
    @SessionScope
    public User sessionUser() {
        return new User();
    }

    /**
     * 全局作用域(Application)
     */
    @Bean
    @ApplicationScope
    public User applicationUser() {
        /**
         * ApplicationScope 和 Singleton 的区别：
         * ApplicationScope 是相对于整个Web容器来说，bean的作用域是ServletContext级别的，ApplicationScope和Singleton有点类似，
         * 区别主要在于：ApplicationScope 是ServletContext的单例，Singleton是一个ApplicationContext的单例
         * 在一个Web容器中,ApplicationContext可以有多个.
         */
        return new User();
    }
}
