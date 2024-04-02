package com.hlb.aop.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.proxy
 * @CreateTime : 2024/4/2 14:37
 * @Description: JDK 动态代理
 * @Author: code_hlb
 */
@Slf4j
public class JDKInvocationHandler implements InvocationHandler {
    /**
     * 相⽐于静态代理来说，动态代理更加灵活,我们不需要针对每个⽬标对象都单独创建⼀个代理对象,⽽是把这个
     * 创建代理对象的⼯作推迟到程序运⾏时由JVM来实现. 也就是说动态代理在程序运⾏时, 根据需要动态创建⽣成.
     * 动态代理的常见实现方法有：
     * 1、JDK 动态代理
     *  1.1 定义⼀个接⼝及其实现类(静态代理中的 HouseSubject 和 RealHouseSubject )
     *  1.2 ⾃定义 InvocationHandler 并重写 invoke ⽅法，在 invoke ⽅法中我们会调⽤⽬标⽅
     *      法(被代理类的⽅法)并⾃定义⼀些处理逻辑
     *  1.3通过 Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h) ⽅法创建代理对象
     * 2、CGLib 动态代理
     */

    private Object target; // 目标对象就是被代理对象

    public JDKInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * 调用目标方法，并对其加强
     * @param proxy 代理类
     * @param method 目标方法
     * @param args 参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理内容
        log.info("JDK 动态代理开始..");
        // 通过反射机制执行被代理类的方法
        Object res = method.invoke(target,args);
        // 代理增强内容
        log.info("JDK 动态代理结束..");
        return res;
    }
}
