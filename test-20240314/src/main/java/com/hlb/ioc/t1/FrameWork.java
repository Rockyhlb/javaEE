package com.hlb.ioc.t1;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.t1
 * @CreateTime : 2024/3/14 10:44
 * @Description: TODO
 * @Author: code_hlb
 */
public class FrameWork {
    // 车身的大小依赖于底盘的尺寸
    private Bottom bottom;
    public FrameWork(int size) {
        bottom = new Bottom(size);
        System.out.println("frameWork init...");
    }
}
