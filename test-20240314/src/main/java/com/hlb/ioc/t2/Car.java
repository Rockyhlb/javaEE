package com.hlb.ioc.t2;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.t1
 * @CreateTime : 2024/3/14 10:41
 * @Description: TODO
 * @Author: code_hlb
 */
public class Car {
    // 传统意义上造车，车依赖于车身的大小
    private FrameWork frameWork;
    public Car(FrameWork frameWork) {
        this.frameWork = frameWork;
        System.out.println("Car init...");
    }
    public void run() {
        System.out.println("Car running...");
    }
}
