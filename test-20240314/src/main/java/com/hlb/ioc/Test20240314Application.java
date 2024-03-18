package com.hlb.ioc;

import com.hlb.ioc.component.UserComponent;
import com.hlb.ioc.controller.UserController;
import com.hlb.ioc.controller.UserController2;
import com.hlb.ioc.model.UserInfo;
import com.hlb.ioc.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// @ComponentScan("com.hlb.ioc")
@SpringBootApplication
public class Test20240314Application {
    public static void main(String[] args) {
        // Spring 是包含众多工具的IOC容器
        // Spring 容器装的是对象，也可以认为 Spring 是包含了众多工具方法的IOC容器
        // Spring 管理的对象称为Bean, ApplicationContext: 上下文，记录Spring 的运行环境
        ApplicationContext context = SpringApplication.run(Test20240314Application.class, args);
        // 五大注解：@Controller  @Service  @Component  @Configuration  @Repository
        // 方法注解：@Bean
        // 五大注解但从功能上看，除了Controller,其它几个效果都差不多，更多的是概念上被赋予了其它意义
        // Controller(@Controller): 控制层，接收前端请求，对请求进行处理并响应数据
        // Service(@Service): 业务逻辑层，处理具体的业务逻辑
        // Dao(@Repository): 数据访问层，也称为持久层，负责数据访问操作，包括数据的增删查改
        // @Component: 用于管理组件
        // @Configuration：用于管理配置信息
        // 五大注解之间的关系：从源码可以看出 其它四个注解都是@Component的衍生类
        // 1、根据类型获取Bean（不适合一个类型多个Bean的情况）
//        UserController userController = context.getBean(UserController.class);
//        userController.welcome();
//        // 2、由于Spring 给我们创建对象时会给每个对象起一个名字，
//        // 命名规则：当我们没有命名时，Spring会按照小驼峰的形式给我们命名，如果类名两位为大写，则Bean的名称为原始类名
//        // 根据对象名称获取Bean (推荐)
//        UserComponent userComponent1 = (UserComponent) context.getBean("userComponent");
//        userComponent1.welcome();
//        // 3、根据类型和名称获取Bean (推荐)
//        UserComponent userComponent2 = context.getBean("userComponent",UserComponent.class);
//        userComponent2.welcome();
//
//        // 由于@Service("userService1")，因此报 “NoSuchBeanDefinitionException”异常
//        // UserService userService1 = (UserService) context.getBean("userService");
//        UserService userService = (UserService) context.getBean("userService1");
//        userService.welcome();
//
//        System.out.println("===============================");
//
//        UserInfo userInfo1 = context.getBean("user1",UserInfo.class);
//        System.out.println(userInfo1);
//        UserInfo userInfo2 = context.getBean("user2",UserInfo.class);
//        System.out.println(userInfo2);
//
//        UserInfo userInfo3 = context.getBean("user3",UserInfo.class);
//        System.out.println(userInfo3);

        // 获取Bean的功能是BeanFactory提供的(源码)，那BeanFactory 和 ApplicationContext 有什么区别呢？
        // 可以从两个方面进行回答：
        // 1、继承方面：
        // BeanFactory：是Spring里面最低层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能；
        // ApplicationContext：应用上下文，继承BeanFactory接口，它是Spring的一各更高级的容器，提供了更多的有用的功能；
        // 2、性能方面：
        // BeanFactory在启动的时候不会去实例化Bean，只有从容器中拿Bean的时候才会去实例化;(懒汉模式)
        // ApplicationContext在启动的时候就把所有的Bean全部实例化了；(饿汉模式)

        // Spring 默认扫描路径是 启动类当前所在路径
        // 可以通过注解 @ComponentScan("com.hlb.ioc") 指定扫描路径

//        UserController bean = context.getBean(UserController.class);
        UserController2 bean = context.getBean(UserController2.class);
        bean.welcome();
    }
}
