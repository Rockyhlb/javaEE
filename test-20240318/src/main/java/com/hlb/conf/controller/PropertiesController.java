package com.hlb.conf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.controller
 * @CreateTime : 2024/3/18 14:17
 * @Description: TODO
 * @Author: code_hlb
 */
@RequestMapping("/prop")
@RestController
public class PropertiesController {
    // 根据@Value注解获取配置文件中的信息
    @Value("${mykey.key1}")
    private String key1;

    // 当直接使用字符串时，就是赋值
    @Value("mykey.key1")
    private String key2;
    @RequestMapping("getKey1")
    public String getKey1() {
        return "获取到的key1: " + key1 + " ,key2: " + key2;
    }
}
