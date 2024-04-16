package com.hlb.principle.beanlife;

import com.hlb.principle.scope.BeanConfig;
import com.hlb.principle.scope.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @BelongsProject: test-20240416
 * @BelongsPackage: com.hlb.principle.beanlife
 * @CreateTime : 2024/4/16 20:26
 * @Description: TODO
 * @Author: code_hlb
 */
@Slf4j
@Component
public class BeanLifeComponent {
    /**
     * 生命周期：一个对象从创建到销毁的整个过程
     * Bean 的 生命周期可以分为以下几个阶段：
     * 1、实例化(为Bean分配内存空间)
     * 2、属性赋值(Bean注入和装配,例如@Autowired)
     * 3、初始化：
     * 1)执行 各种通知：如BeanNameAware / BeanFactoryAware / BeanClassLoaderAware的接口方法
     * 2)执行 初始化方法：xml中定义的内容 / 使用注解的方式(@PostConstruct) / 执行初始化后置方法(BeanPostProcessor)
     * 4、使用Bean
     * 5、销毁Bean: 如@PreDestroy / DisposableBean接口方法，destroy-method.
     * 实例化和属性赋值对应构造方法和setter方法的注入，初始化和销毁是用户能自定义扩展的两个阶段，
     * 可以在实例化之后，类加载完成之前进行自定义‘事件’的处理
     */
    private User user;

    public BeanLifeComponent() {
        log.info("执行构造函数.....");
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
        log.info("执行setter()方法.....");
    }

    @PostConstruct
    public void init() {
        log.info("执行init（）方法....");
    }

    public void use() {
        log.info("执行use()方法");
    }

    @PreDestroy
    public void destroy() {
        log.info("执行destroy()方法");
    }
}
