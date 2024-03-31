package com.hlb.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.controller
 * @CreateTime : 2024/3/31 22:10
 * @Description: TestController
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/t1")
    public String t1() {
        log.info("执行t1方法...");
        return "t1";
    }

    @RequestMapping("/t2")
    public String t2() {
        log.info("执行t2方法...");
        // 构造异常
        int a = 10 / 0;
        return "t2";
    }
}
