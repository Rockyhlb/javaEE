package com.hlb.aop.proxy;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.proxy
 * @CreateTime : 2024/4/2 8:36
 * @Description: 代理模式-业务实现类(被代理对象)
 * @Author: code_hlb
 */
public class RealHouseSubject implements HouseSubject {
    @Override
    public void rentHouse() {
        System.out.println("我是房东，我要出租房子...");
    }

    @Override
    public void saleHouse() {
        System.out.println("我是房东，我要出租房子...");
    }
}
