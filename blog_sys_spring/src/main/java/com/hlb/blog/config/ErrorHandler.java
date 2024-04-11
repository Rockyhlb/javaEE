package com.hlb.blog.config;

import com.hlb.blog.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.config
 * @CreateTime : 2024/4/11 15:20
 * @Description: 统一异常处理
 * @Author: code_hlb
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    public Result<Object> handler(Exception e) {
        log.error("发生异常, e:", e);
        return Result.fail(e.getMessage());
    }
}
