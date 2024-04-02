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
@Order(1)
//@Component
public class AspectDemo5 {
    @Pointcut("execution(* com.hlb.aop.controller.*.*(..))")
    public void pt() {
    }

    @Before("pt()")
    // 前置通知
    public void doBefore() {
        log.info("正在执行 AspectDemo5.doBefore 方法...");
    }

    @After("pt()")
    // 前置通知
    public void doAfter() {
        log.info("正在执行 AspectDemo5.doAfter 方法...");
    }
}
