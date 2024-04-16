package com.hlb.principle;

import com.hlb.principle.scope.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Test20240416Application {
    public static void main(String[] args) {
        // 从 Spring容器中获取 Bean
        ApplicationContext context = SpringApplication.run(Test20240416Application.class, args);
//        System.out.println(context.getBean(User.class));
    }
}
