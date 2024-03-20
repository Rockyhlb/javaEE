package com.hlb.conf.facade;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.facade
 * @CreateTime : 2024/3/20 15:18
 * @Description: 对象-卧室
 * @Author: code_hlb
 */
public class BedRoomLight implements Light{
    @Override
    public void turnOn() {
        System.out.println("打开卧室灯..");
    }

    @Override
    public void turnOff() {
        System.out.println("关闭卧室灯..");
    }
}
