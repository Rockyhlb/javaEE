package com.hlb.aop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.aspect
 * @CreateTime : 2024/4/1 22:08
 * @Description: 编写自定义注解
 * @Author: code_hlb
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeRecord {
    /**
     * '@Target' 标识了 Annotation 所修饰的对象范围, 即该注解可以用在什么地⽅.
     * 常⽤取值:
     * ElementType.TYPE: ⽤于描述类、接⼝(包括注解类型) 或enum声明
     * ElementType.METHOD: 描述方法
     * ElementType.PARAMETER: 描述参数
     * ElementType.TYPE_USE: 可以标注任意类型
     * ---------☆---------
     * '@Retention' 指Annotation被保留的时间⻓短, 标明注解的⽣命周期
     * 常用取值：
     * 1. RetentionPolicy.SOURCE：表⽰注解仅存在于源代码阶段, 编译成字节码后就会被丢弃.
     * 因此在运⾏时⽆法获取到该注解的信息, 只能在编译时使⽤.
     * ⽐如 @SuppressWarnings , 以及lombok提供的注解 @Data , @Slf4j
     * 2. RetentionPolicy.CLASS：编译时注解，表⽰注解存在于源代码和字节码阶段, 但在运⾏时会被丢
     * 弃. 这意味着在编译时和字节码中可以通过反射获取到该注解的信息, 但在实际运⾏时无法获取，通常⽤于⼀些框架和⼯具的注解.
     * 3. RetentionPolicy.RUNTIME：运⾏时注解. 表⽰注解存在于源代码, 字节码和运⾏时阶段.
     * 因此在编译时, 字节码中和实际运⾏时都可以通过反射获取到该注解的信息.
     * 通常⽤于⼀些需要在运⾏时处理的注解, 如Spring的 @Controller、@ResponseBody
     */
}