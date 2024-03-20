package com.hlb.conf.facade;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.facade
 * @CreateTime : 2024/3/20 15:20
 * @Description: 门面模式的实现
 * @Author: code_hlb
 */
public class FacadeDemo {
    public static void main(String[] args) throws InterruptedException {
        // 通过门面模式的一个开关就可以控制所有子组件的开关
        LightFacade lightFacade = new LightFacade();
        lightFacade.on();
        Thread.sleep(3000);
        System.out.println("3s later..");
        lightFacade.off();
        /*
        * 门面模式的优点：
        * 1、降低了系统间的耦合程度
        * 2、简化了客户端对子系统的使用难度，客户端无需关心子系统的具体实现方式
        * */
    }
}
