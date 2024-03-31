package com.hlb.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.aspect
 * @CreateTime : 2024/3/31 21:42
 * @Description: AspectDemo
 * @Author: code_hlb
 */

@Slf4j
@Aspect   // 标识这是一个切面类
//@Component
public class AspectDemo {
    /**
     * ---------☆---------
     * AOP: Aspect Oriented Programming (面向切面编程)，是Spring框架的第二大核心，
     * 切面就是指某一类问题，统一功能处理中的拦截器、统一数据返回格式和统一异常处理，都是AOP思想的实现
     * 简单来说，AOP就是一种 对某一类事情集中处理 的思想，
     * AOP的实现方式有很多，常见的有：SpringAop、AspectJ、CGlib等
     * 优点：可以在不修改源码的情况下对已有方法进行增强，无侵入性(解耦合)，
     * 同时减少了代码的重复，提高了开发效率和维护效率
     * ---------☆---------
     * "execution(* com.hlb.book.controller.*.*(..))"：切点表达式(PointCut)，说明对哪些方法生效(进行增强)
     * 'ProceedingJoinPoint pj'：连接点，满足切点表达式规则的方法都是连接点
     * '通知'：方法体中的内容，切面具体要做的事情
     * '切面(Aspect)'：切点(PointCut)+通知(Advice)
     * 通知类型：什么时候执行通知中的内容
     * 1、@Around: 环绕通知, 此注解标注的通知方法在目标方法前,后 都被执行
     * 2、@Before: 前置通知, 此注解标注的通知方法在目标方法前被执行
     * 3、@After: 后置通知, 此注解标注的通知方法在目标方法后被执行,无论是否有异常都会执行
     * 4、@AfterReturning: 返回后通知, 此注解标注的通知方法在目标方法后被执行, 有异常不会执行
     * 5、@AfterThrowing: 异常后通知, 此注解标注的通知方法发生异常后执行
     * ---------☆---------
     * 1)当没有出现异常时返回：                                 2)当出现异常时返回：
     * 执行 AspectDemo.doAround 方法前...                     执行 AspectDemo.doAround 方法前...
     * 正在执行 AspectDemo.doBefore 方法...                   正在执行 AspectDemo.doBefore 方法...
     * 执行t1方法...                                         执行t2方法...
     * 正在执行 AspectDemo.doAfterReturning 方法...           正在执行 AspectDemo.doAfterThrowing 方法...
     * 正在执行 AspectDemo.doAfter 方法...                    正在执行 AspectDemo.doAfter 方法...
     * 执行 AspectDemo.doAround 方法后...                     发生异常，然后执行 AspectDemo.doAround 方法后...
     */
    @Before("execution(* com.hlb.aop.controller.*.*(..))")
    // 前置通知
    public void doBefore() {
        log.info("正在执行 AspectDemo.doBefore 方法...");
    }

    // 通过@PointCut 注解, 把公共的切点表达式提取出来, 需要用时引用该切入点表达式即可.
    @Pointcut("execution(* com.hlb.aop.controller.*.*(..))")
    public void pt() {
        // 使⽤private修饰时, 仅能在当前切面类中使⽤, 当其他切⾯类也要使⽤当前切点定义时,
        // 就需要把private改为public. 引⽤方式为: 全限定类名.方法名()
    }

    @After("pt()")
    // 前置通知
    public void doAfter() {
        log.info("正在执行 AspectDemo.doAfter 方法...");
    }

    @AfterReturning("pt()")
    // 前置通知
    public void doAfterReturning() {
        log.info("正在执行 AspectDemo.doAfterReturning 方法...");
    }

    @AfterThrowing("pt()")
    // 前置通知
    public void doAfterThrowing() {
        log.info("正在执行 AspectDemo.doAfterThrowing 方法...");
    }

    @Around("pt()")
    // 前置通知
    public Object doAround(ProceedingJoinPoint joinPoint) {
        /**
         * '@Around' 环绕通知需要调⽤ ProceedingJoinPoint.proceed() 来让原始方法执⾏, 其他
         * 通知不需要考虑目标方法执行.
         * '@Around' 环绕通知方法的返回值, 必须指定为Object, 来接收原始方法的返回值, 否则原始方法执
         * 行完毕, 是获取不到返回值的.
         */
        log.info("执行 AspectDemo.doAround 方法前...");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error(joinPoint.toShortString() + "发生异常，e:{}", throwable.getMessage());
        }
        log.info("执行 AspectDemo.doAround 方法后...");
        return result;
    }

//    @Around("pt()")
//    // 前置通知
//    public Object doAround1(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object result = null;
//        long start = System.currentTimeMillis();
//        result = joinPoint.proceed();
//        // String com.hlb.aop.controller.TestController.t1() cost time:11ms
//        log.info(joinPoint.getSignature() + " cost time:" + (System.currentTimeMillis() - start) + "ms");
//        // String com.hlb.aop.controller.TestController.t1()
//        log.info(joinPoint.getSignature().toString());
//        // public java.lang.String com.hlb.aop.controller.TestController
//        log.info(joinPoint.getSignature().toLongString());
//        // TestController.t1()
//        log.info(joinPoint.getSignature().toShortString());
//        // []
//        log.info(Arrays.toString(joinPoint.getArgs()));
//        return result;
//    }
}
