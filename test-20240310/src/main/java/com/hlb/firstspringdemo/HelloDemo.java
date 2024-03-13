package com.hlb.firstspringdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: FirstSpringDemo
 * @BelongsPackage: com.hlb.firstspringdemo
 * @CreateTime : 2024/3/10 14:54
 * @Description: TODO
 * @Author: code_hlb
 */
@RestController
public class HelloDemo {
    @RequestMapping("/hello")
    public String hello() {
        return "hello,SpringBoot!";
    }
    @RequestMapping("helloCN")
    public String helloCN() {
        return "你好，SpringBoot";
    }
}
