package com.hlb.conf.facade;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.facade
 * @CreateTime : 2024/3/20 15:19
 * @Description: TODO
 * @Author: code_hlb
 */
public class LightFacade {
    // 实现门面模式，完成控制所有房间灯的打开与关闭
    LivingRoomLight livingRoomLight = new LivingRoomLight();
    KitchenLight kitchenLight = new KitchenLight();
    BedRoomLight bedRoomLight = new BedRoomLight();
    public void on() {
        livingRoomLight.turnOn();
        kitchenLight.turnOn();
        bedRoomLight.turnOn();
    }

    public void off() {
        livingRoomLight.turnOff();
        kitchenLight.turnOff();
        bedRoomLight.turnOff();
    }
}
