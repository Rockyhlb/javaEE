package com.hlb.aop.proxy;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.proxy
 * @CreateTime : 2024/4/2 14:08
 * @Description: 代理模式-代理类
 * @Author: code_hlb
 */
public class HouseProxy implements HouseSubject {
    /**
     * 代理模式, 也叫委托模式.
     * 定义：为其他对象提供⼀种代理以控制对这个对象的访问. 它的作⽤就是通过提供⼀个代理类, 让我们
     * 在调⽤⽬标⽅法的时候, 不再是直接对⽬标⽅法进⾏调⽤, ⽽是通过代理类间接调⽤.
     * <p>
     * 代理模式可以在不修改被代理对象的基础上, 通过扩展代理类, 进⾏⼀些功能的附加与增强.
     * 根据代理的创建时期, 代理模式分为静态代理和动态代理.
     * 1、静态代理: 由程序员创建代理类，程序运⾏前代理类的 .class ⽂件就已经存在了.
     * 2、动态代理: 在程序运⾏时, 运⽤反射机制动态创建⽽成
     */
    private HouseSubject houseSubject;

    public HouseProxy(HouseSubject houseSubject) {
        this.houseSubject = houseSubject;
    }

    @Override
    public void rentHouse() {
        System.out.println("我是中介，开始代理..");
        houseSubject.rentHouse();
        System.out.println("我是中介，代理结束..");
    }

    @Override
    public void saleHouse() {
        System.out.println("我是中介，开始代理..");
        houseSubject.saleHouse();
        System.out.println("我是中介，代理结束..");
    }
}
