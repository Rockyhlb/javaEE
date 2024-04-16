package com.hlb.principle.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240416
 * @BelongsPackage: com.hlb.principle
 * @CreateTime : 2024/4/16 18:11
 * @Description: TODO
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class BeanController {
    // 在代码中直接注入 ApplicationContext 获取Spring容器
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private User singleUser;

    @Autowired
    private User prototypeUser;

    @Autowired
    private User requestUser;

    @Autowired
    private User sessionUser;

    @Autowired
    private User applicationUser;

    @RequestMapping("/getUser")
    public String getUser() {
        User user = applicationContext.getBean(User.class);
        User user1 = applicationContext.getBean(User.class);
        // 两次都输出：com.hlb.principle.scope.User@7b44b63d
        // 说明默认情况下，Spring容器中的Bean都是单例的，这种模式也就是 Bean 的作用域
        // Bean 的作用域就是指 Bean 在Spring框架中的某种行为模式
        System.out.println(user);
        System.out.println(user1);
        return "dog: " + user + "  " + "dog1: " + user1;
    }

    /**
     * 在Spring中⽀持6种作用域，后4种在Spring MVC环境才⽣效
     * 1、singleton：单例作用域
     * 2、prototype：原型作用域 (多例作用域)
     * 3、request：请求作用域 (每次请求获取的是不同的对象，同一个请求的对象相同)
     * 4、session：会话作用域 (在同一个对话中，获取到的是同一个对象)
     * 5、Application: 全局作用域
     * 6、websocket：HTTP WebSocket 作用域
     */

    /**
     * 单例作用域
     */
    @RequestMapping("/single")
    public String single() {
        // @Autowired 和 ApplicationContext.getBean都是从Spring容器当中获取对象
        User contextUser = (User) applicationContext.getBean("singleUser");
        // 多次访问, 得到的都是同一个对象, 并且 @Autowired 和 applicationContext.getBean()也是同一个对象.
        return "singleUser:" + singleUser.toString() + ", contextUser:" + contextUser.toString();
    }

    /**
     * 原型作用域 (多例作用域)：多次获取的是不同的对象
     */
    @RequestMapping("/prototype")
    public String prototypeUser() {
        User contextUser = (User) applicationContext.getBean("prototypeUser");
        // 由于prototypeUser在Spring容器启动时，就已经注入了 ，所以多次请求也不会发生变化
        // contextUser每次获取的对象都不一样
        return "prototypeUser:" + prototypeUser.toString() + ", contextUser:" + contextUser.toString();
    }

    /**
     * 请求作用域：不同请求获取不同对象，同一个请求获取相同对象
     */
    @RequestMapping("/request")
    public String requestUser() {
        User contextUser = (User) applicationContext.getBean("requestUser");
        // 在一次请求中, @Autowired 和 applicationContext.getBean() 也是同一个对象.
        // 但是每次请求, 都会重新创建对象
        return "requestUser:" + requestUser.toString() + ", contextUser:" + contextUser.toString();
    }

    /**
     * 会话作用域：不同会话获取不同对象，同一个会话获取相同对象
     */
    @RequestMapping("/session")
    public String sessionUser() {
        User contextUser = (User) applicationContext.getBean("sessionUser");
        return "sessionUser:" + sessionUser.toString() + ", contextUser:" + contextUser.toString();
    }

    /**
     * 全局作用域(Application)：在一个应用中，获取到的都是同一个对象
     */
    @RequestMapping("/application")
    public String applicationUser() {
        User contextUser = (User) applicationContext.getBean("applicationUser");
        return "applicationUser:" + applicationUser.toString() + ", contextUser:" + contextUser.toString();
    }
}
