package com.hlb.aop.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.proxy
 * @CreateTime : 2024/4/2 14:22
 * @Description: 测试类
 * @Author: code_hlb
 */
public class Test {
    public static void main1(String[] args) {
        // 静态代理
        HouseSubject proxy = new HouseProxy(new RealHouseSubject());
        proxy.rentHouse();
        proxy.saleHouse();
    }

    public static void main2(String[] args) {
        // 测试JDK动态代理
        // 目标类
        HouseSubject target = new RealHouseSubject();
        // 生成代理对象,newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        // Loader: 类加载器, ⽤于加载代理对象.
        // interfaces : 被代理类实现的⼀些接⼝(这个参数的定义, 也决定了JDK动态代理只能代理实现了接⼝的⼀些类)
        // h : 实现了 InvocationHandler 接⼝的对象
        HouseSubject jdkProxy = (HouseSubject) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{HouseSubject.class},
                new JDKInvocationHandler(target)
        );
        jdkProxy.rentHouse();
        jdkProxy.saleHouse();
        // 使用JDK动态代理 未实现接口 的类会报错: com.hlb.aop.proxy.RealHouseSubject2 is not an interface
        RealHouseSubject2 target2 = new RealHouseSubject2();
        RealHouseSubject2 jdkProxy2 = (RealHouseSubject2) Proxy.newProxyInstance(
                target2.getClass().getClassLoader(),
                new Class[]{RealHouseSubject2.class},
                new JDKInvocationHandler(target2)
        );
        jdkProxy2.rentHouse();
        jdkProxy2.saleHouse();
    }

    public static void main3(String[] args) {
        // 测试 CGLib 动态代理(实现接口类)
        HouseSubject target = new RealHouseSubject();
        HouseSubject proxy = (HouseSubject) Enhancer.create(
                target.getClass(),
                new CGLibMethodInterceptor(target)
        );
        proxy.rentHouse();
        proxy.saleHouse();

        // 测试 CGLib 动态代理(非接口类)
        RealHouseSubject2 target2 = new RealHouseSubject2();
        RealHouseSubject2 proxy2 = (RealHouseSubject2) Enhancer.create(
                target2.getClass(),
                new CGLibMethodInterceptor(target2)
        );
        proxy2.rentHouse();
        proxy2.saleHouse();
    }
}
