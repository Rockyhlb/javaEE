package com.hlb.conf.controller;

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
@RequestMapping("/log")
public class LoggerController {
    // 1、获取日志对象
    // 传入的参数：LoggerController.class，是为了标准这个日志的名称，这样就可以知道是哪个类输出的日志
    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    /**
     * 日志门面(Logging Facade)：Slf4j  commons-logging
     * 日志框架(实现)：Log4j Jul Logback
     * 日志门面并不是一个真正的日志实现，而是一个抽象层，对日志框架 制定的一种规范，标准，接口.
     * Slf4j 就是其他日志框架的门面. Slf4j 可以理解为是提供日志服务的统一API接口,并不涉及到具体的日志逻辑实现
     * 门面模式：又称为外观模式，提供了一个统一的接口，用来访问子系统的一群接口.
     */
    @RequestMapping("/printLog")
    public String pringLog() {
        // 普通方式打印日志
        System.out.println("普通打印日志");
        // 通过SpringBoot 集成的日志框架 Slf4j 输出日志
        // 通过日志对象输出日志
        logger.info("这是Slf4j 打印的日志");
        return "success";
    }

    @RequestMapping("logLevel")
    public String logLevel() {
        // 日志级别,从高到低依次是:
        // Fatal: 致命信息，表示需要立即被处理的系统级错误
        // Error: 错误信息，级别较高的错误日志，但仍不影响系统的继续运行
        // Warn: 警告信息，不影响使⽤, 但需要注意的问题
        // Info: 普通信息，⽤于记录应⽤程序正常运⾏时的⼀些信息
        // Debug: 调试信息，调试时候的信息打印
        // Trace: 追踪信息，更细粒度的日志信息
        // SpringBoot 默认的⽇志框架是Logback, Logback没有 FATAL 级别, 它被映射到 ERROR.
        logger.error("这是 error 级别的日志");
        logger.warn("这是 warn 级别的日志");
        logger.info("这是 info 级别的日志");
        // ⽇志的输出级别默认是 info, 因此只会打印⼤于等于此级别的⽇志, 也就是 info, warn和error.
        logger.debug("这是 debug 级别的日志");
        logger.trace("这是 trace 级别的日志");
        return "success";
    }
}
