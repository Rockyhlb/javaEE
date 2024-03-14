package com.hlb.ioc.t2;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.t1
 * @CreateTime : 2024/3/14 10:47
 * @Description: TODO
 * @Author: code_hlb
 */
public class Bottom {
    // 底盘的大小又依赖于轮胎的尺寸
    private Tire tire;
    public Bottom(Tire tire) {
        this.tire = tire;
        System.out.println("bottom init...");
    }
}
