package com.hlb.aop.controller;

import com.hlb.aop.aspect.TimeRecord;
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
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/u1")
    public String u1() {
        log.info("执行u1方法...");
        return "u1";
    }

    @TimeRecord   // 使用该注解
    @RequestMapping("/u2")
    public String u2() {
        log.info("执行u2方法...");
        return "u2";
    }
}
