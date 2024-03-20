package com.hlb.conf.facade;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.facade
 * @CreateTime : 2024/3/20 15:12
 * @Description: 提供灯开关的接口，所有灯都应该实现该接口
 * @Author: code_hlb
 */
public interface Light {
    void turnOn();
    void turnOff();
}
