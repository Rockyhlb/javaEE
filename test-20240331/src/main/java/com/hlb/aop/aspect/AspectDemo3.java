package com.hlb.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.aspect
 * @CreateTime : 2024/3/31 21:42
 * @Description: AspectDemo
 * @Author: code_hlb
 */
@Slf4j
@Aspect
@Order(3)  // order 值越小，优先级越高
@Component
public class AspectDemo3 {
    // 通过 @Order 注解控制 切面通知的执行顺序
    @Pointcut("execution(* com.hlb.aop.controller.*.*(..))")
    public void pt() {
    }

    @Before("pt()")
    // 前置通知
    public void doBefore() {
        // order 越小 越先执行
        log.info("正在执行 AspectDemo3.doBefore 方法...");
    }

    @After("pt()")
    // 前置通知
    public void doAfter() {
        // order 越小 越晚执行
        log.info("正在执行 AspectDemo3.doAfter 方法...");
    }
}
