package com.hlb.conf.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.controller
 * @CreateTime : 2024/3/20 14:51
 * @Description: TODO
 * @Author: code_hlb
 */
@RestController
// 引入Lombok 的 slf4j 注解，就可以省去类中创建日志对象的过程了
@Slf4j
@RequestMapping("/log2")
public class LoggerController2 {
    @RequestMapping("logLevel")
    public String logLevel() {
        // @Slf4j注解的生命周期为：@Retention(RetentionPolicy.SOURCE)，也就是只在源码阶段
        // 代码进行编译时就会将该注解转换为创建该类的日志对象
        log.error("这是 error 级别的日志");
        log.warn("这是 warn 级别的日志");
        log.info("这是 info 级别的日志");
        log.debug("这是 debug 级别的日志");
        log.trace("这是 trace 级别的日志");
        return "success";
    }
}
