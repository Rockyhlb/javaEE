package com.hlb.aop.component;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.component
 * @CreateTime : 2024/4/2 19:09
 * @Description: TODO
 * @Author: code_hlb
 */
public interface IFace {
    /**
     * SpringAOP 使用的是哪种代理:
     * 相同点：Spring Framework 和 Spring Boot 底层都是JDK 和 CGLib 一起使用
     * 不同点：
     * 1、Spring Framework: 如果代理的是接口，使用JDK动态代理，如果代理的是没有实现接口的类，使用的是CGLib
     * 2、SpringBoot:
     *  在SpringBoot 2.X 之后的版本，默认配置使用的都是CGLib代理，代理的类无论是否实现了接口，都使用CGLib,
     *  如果需要使用JDK代理，需要进行设置
     *  在SpringBoot 2.X 之前的版本，默认使用的也是JDK代理，和Spring FrameWork 一样。
     * 它们的代理之所以不同，主要是因为 proxyTargetClass 默认配置不一样导致的
     */
    // 如果beanClass实现了接⼝，且接口至少有⼀个自定义⽅法，则使⽤JDK代理
    // 否则仍然是CGLIB代理( 设置ProxyTargetClass为true(默认使用CGLib代理) )
    void sayHi();
}
