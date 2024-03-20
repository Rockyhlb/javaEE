package com.hlb.conf.facade;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.facade
 * @CreateTime : 2024/3/20 15:14
 * @Description: 对象-客厅灯
 * @Author: code_hlb
 */
public class LivingRoomLight implements Light{
    @Override
    public void turnOn() {
        System.out.println("打开客厅灯..");
    }

    @Override
    public void turnOff() {
        System.out.println("关闭客厅灯..");
    }
}
