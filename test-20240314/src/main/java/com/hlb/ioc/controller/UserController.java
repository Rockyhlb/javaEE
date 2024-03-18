package com.hlb.ioc.controller;

import com.hlb.ioc.component.UserComponent;
import com.hlb.ioc.config.BeanConfig;
import com.hlb.ioc.config.UserConfig;
import com.hlb.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.controller
 * @CreateTime : 2024/3/14 12:20
 * @Description: 类注解：@Controller
 * @Author: code_hlb
 */
@Controller
public class UserController {

    // DI(Dependency injection): 依赖注入，简单来说就是将对象取出来放到某个类的属性中
    // Spring 提供了三种依赖注入的方式，分别是：
    // 1、属性注入 @Autowired,
    // 优点：简单，使用方便
    // 缺点：只能用于IOC容器，不能注入一个final修饰的属性
    @Autowired
    private UserService userService;

    // 2、构造方法注入(Spring4推荐)
    // 优点：可以注入final修饰的属性，注入的对象使用前就被初始化，构造方法是JDK支持的，所以更换框架也适用
    // 缺点：注入多个对象时比较繁琐
    private UserComponent userComponent;
    // 如果类只有⼀个构造⽅法，那么 @Autowired 注解可以省略；
    // 如果类中有多个构造⽅法，那么需要添加上 @Autowired 来明确指定到底使⽤哪个构造⽅法
    @Autowired
    public UserController(UserComponent userComponent) {
        this.userComponent = userComponent;
    }

    public UserController() {
    }

    // 3、setter方法注入(Spring3推荐)
    // 优点：方便在类实例之后，重新对该对象进行配置或者注入
    // 缺点：不能注入final修饰的属性，存在注入对象被多次改变的风险
    private UserConfig userConfig;
    // setter方法注入仍然需要加入 @Autowired 注解
    @Autowired
    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public void welcome() {
        System.out.println("hello,UserController!");
        userService.welcome();
        userComponent.welcome();
        userConfig.welcome();
    }
}
