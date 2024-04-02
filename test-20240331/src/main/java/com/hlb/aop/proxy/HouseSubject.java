package com.hlb.aop.proxy;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.proxy
 * @CreateTime : 2024/4/2 8:35
 * @Description: 代理模式-业务接口类
 * @Author: code_hlb
 */
public interface HouseSubject {
    void rentHouse();
    // 静态代理时，当我们需要新加功能时，我们的业务接口类，业务实现类，代理类都需要跟着一起修改，
    // 但是我们代理类的逻辑都是一样的，因此我们就可以引入动态代理来来统一管理代理流程
    void saleHouse();
}
