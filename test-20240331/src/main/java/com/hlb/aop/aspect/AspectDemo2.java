package com.hlb.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
//@Component
public class AspectDemo2 {
    /**
     * 当有多个切面类时，执行顺序为(优先级默认为按照名称排序)：
     * 执行 AspectDemo.doAround 方法前...
     * 正在执行 AspectDemo.doBefore 方法...
     * 正在执行 AspectDemo2.doBefore 方法...
     * 执行t1方法...
     * 正在执行 AspectDemo2.doAfter 方法...
     * 正在执行 AspectDemo.doAfterReturning 方法...
     * 正在执行 AspectDemo.doAfter 方法...
     * 执行 AspectDemo.doAround 方法后...
     * 可以看出：
     * '@Before' 通知：字⺟排名靠前的先执⾏
     * '@After' 通知：字⺟排名靠前的后执⾏
     */
    @Pointcut("execution(* com.hlb.aop.controller.*.*(..))")
    public void pt() {
    }

    @Before("pt()")
    // 前置通知
    public void doBefore() {
        log.info("正在执行 AspectDemo2.doBefore 方法...");
    }

    @After("pt()")
    // 前置通知
    public void doAfter() {
        log.info("正在执行 AspectDemo2.doAfter 方法...");
    }
}
