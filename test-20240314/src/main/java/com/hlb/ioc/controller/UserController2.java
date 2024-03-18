package com.hlb.ioc.controller;

import com.hlb.ioc.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.controller
 * @CreateTime : 2024/3/18 11:27
 * @Description: TODO
 * @Author: code_hlb
 */
@Controller
public class UserController2 {
    // @Autowired 存在的问题：当注入的类型存在多个对象时，可能会报错  可以使用 @Primary @Qualifier @Resource 三种注解进行解决
    // @Qualifier("user1") // 指定注入的Bean名称
    @Resource(name = "user2")
    @Autowired
    private UserInfo userInfo;
    public void welcome() {
        System.out.println("hello,userController2!");
        System.out.println(userInfo);
        // 问：@Autowird 与 @Resource的区别
        // @Autowired 是Spring框架提供的注解，⽽@Resource是JDK提供的注解
        // @Autowired 默认是按照类型注⼊，⽽@Resource默认是按照名称注⼊.
        // 相⽐于 @Autowired 来说，@Resource ⽀持更多的参数设置，
    }
}
