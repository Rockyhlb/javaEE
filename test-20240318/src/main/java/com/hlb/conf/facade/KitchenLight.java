package com.hlb.conf.facade;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.facade
 * @CreateTime : 2024/3/20 15:16
 * @Description: 对象-厨房
 * @Author: code_hlb
 */
public class KitchenLight implements Light{
    @Override
    public void turnOn() {
        System.out.println("打开厨房灯..");
    }

    @Override
    public void turnOff() {
        System.out.println("关闭厨房灯..");
    }
}
