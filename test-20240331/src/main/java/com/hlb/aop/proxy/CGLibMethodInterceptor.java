package com.hlb.aop.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.proxy
 * @CreateTime : 2024/4/2 15:08
 * @Description: CGLib 动态代理
 * @Author: code_hlb
 */
@Slf4j
public class CGLibMethodInterceptor implements MethodInterceptor {
    /**
     * CGLIB(Code Generation Library)是⼀个基于ASM的字节码⽣成库，它允许我们在运⾏时对字节码进⾏
     * 修改和动态⽣成. CGLIB 通过继承⽅式实现代理, 很多知名的开源框架都使⽤到了CGLIB. 例如 Spring
     * 中的 AOP 模块中: 如果⽬标对象实现了接⼝，则默认采⽤ JDK 动态代理, 否则采⽤ CGLIB 动态代理.
     * 实现步骤：
     * 1、定义⼀个类(被代理类)
     * 2、⾃定义 MethodInterceptor 并重写 intercept ⽅法， intercept ⽤于增强目标⽅法
     * 和 JDK 动态代理中的 invoke ⽅法类似
     * 3、通过 Enhancer 类的 create()创建代理类
     */
    private Object target;

    public CGLibMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 调用目标方法, 并对目标方法进行功能增强
     * '@param o  代理类
     * '@param method  目标方法
     * '@param objects 参数
     * '@param methodProxy
     * '@return
     * '@throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("CGLib 开始动态代理..");
        Object res = methodProxy.invoke(target,objects);
        log.info("CGLib 动态代理结束..");
        return res;
    }
}
