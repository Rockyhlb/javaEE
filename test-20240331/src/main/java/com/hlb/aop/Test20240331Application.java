package com.hlb.aop;

import com.hlb.aop.component.IFace;
import com.hlb.aop.component.UserComponent;
import com.hlb.aop.component.UserComponent2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Test20240331Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Test20240331Application.class, args);
        // SpringBoot 默认情况下, 接口类和普通类都可以被正确代理
//        UserComponent bean = context.getBean(UserComponent.class);
//        System.out.println(bean.getClass().toString());
//
//        UserComponent2 bean2 = (UserComponent2) context.getBean("userComponent2");
//        System.out.println(bean2.getClass().toString());

        //设置JDK代理
		UserComponent bean3 = context.getBean(UserComponent.class);
		System.out.println(bean3.getClass().toString());  // JDK不能正常代理

        IFace bean4 = (IFace) context.getBean("userComponent");
        System.out.println(bean4.getClass().toString());  //JDK可以正常代理

		UserComponent2 bean5 = (UserComponent2) context.getBean("userComponent2");
		System.out.println(bean5.getClass().toString());  // CGLib代理
    }
}
