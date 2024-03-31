package com.hlb.book.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.aspect
 * @CreateTime : 2024/3/31 20:36
 * @Description: 通过Aop实现切面类 -- 统计图书系统中各个接口的执行时间
 * @Author: code_hlb
 */
@Slf4j
@Aspect   // 标识这是一个切面类
@Component
public class TimeRecordAspect {
    /**
     * AOP: Aspect Oriented Programming (面向切面编程)，是Spring框架的第二大核心，
     * 切面就是指某一类问题，统一功能处理中的拦截器、统一数据返回格式和统一异常处理，都是AOP思想的实现
     * 简单来说，AOP就是一种 对某一类事情集中处理 的思想，
     * AOP的实现方式有很多，常见的有：SpringAop、AspectJ、CGlib等
     * 优点：可以在不修改源码的情况下对已有方法进行增强，无侵入性(解耦合)，
     *      同时减少了代码的重复，提高了开发效率和维护效率
     * 核心概念：
     * 切面：切点+通知
     * 切点：通知类型+切点表达式
     * 通知、通知类型
     */
    @Around("execution(* com.hlb.book.controller.*.*(..))")  // 切点
    public Object record(ProceedingJoinPoint pj) throws Throwable {
        // '@Around': 通知类型，环绕通知，表示在目标方法的前后都会被执行
        // 记录方法耗时
        long start = System.currentTimeMillis();
        // 执行目标方法
        Object result = pj.proceed();
        long end = System.currentTimeMillis();
        log.info(pj.getSignature() + "执行耗时：{}ms", end - start);
        return result;
    }
}
