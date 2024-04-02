package com.hlb.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.aspect
 * @CreateTime : 2024/3/31 21:42
 * @Description: AspectDemo6
 * @Author: code_hlb
 */

@Slf4j
@Aspect
//@Component
public class AspectDemo6 {
    /**
     * 切点表达式常⻅有两种表达⽅式
     * 1. execution(RR)：根据⽅法的签名来匹配
     * execution(* com.hlb.aop.controller.*.*(..))
     * execution(<访问修饰符> <返回类型> <包名.类名.方法(方法参数)> <异常>)
     * 其中访问修饰符 和 异常 可以省略，
     * * ：匹配任意字符，只匹配⼀个元素(返回类型, 包, 类名, ⽅法或者⽅法参数)
     * .. ：匹配多个连续的任意符号, 可以通配任意层级的包, 或任意类型, 任意个数的参数
     * 2. @annotation(RR) ：根据注解匹配
     * 实现步骤：
     * 1) 自定义注解
     * 2) 使用 @Annotation表达式 描述切点，实现该注解需要完成的功能
     * 3) 使用注解
     */
    @Around("@annotation(com.hlb.aop.aspect.TimeRecord)")  // 使用 @Annotation表达式 描述切点 --> 记录方法的执行时间
    public Object timeRecorde(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object res = null;
        try {
            res = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("发生异常，e:{}", throwable.getMessage());
        }
        log.info(joinPoint.toShortString() + " cost time:" + (System.currentTimeMillis() - start) + "ms");
        return res;
    }
}
