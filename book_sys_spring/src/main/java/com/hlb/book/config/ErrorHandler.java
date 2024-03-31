package com.hlb.book.config;

import com.hlb.book.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.config
 * @CreateTime : 2024/3/30 23:41
 * @Description: 统一异常处理
 * @Author: code_hlb
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ErrorHandler {
    /**
     * 捕获异常，统一发生异常后 的 返回结果处理
     * 当有多个异常通知时，匹配顺序为当前类及其⼦类向上依次匹配
     */
    @ExceptionHandler
    public Result handler(Exception e) {
        log.error("捕获异常：e: ", e);
        return Result.fail("内部发生错误，请联系管理员进行处理！");
    }

    @ExceptionHandler
    public Result handler(NullPointerException e) {
        log.error("捕获异常, e:", e);
        return Result.fail("内部发生NullPointerException错误");
    }

    @ExceptionHandler
    public Result handler(SQLException e) {
        log.error("捕获异常, e:", e);
        return Result.fail("数据库内部发生错误");
    }

    @ExceptionHandler
    public Result handler(RuntimeException e) {
        log.error("捕获异常, e:", e);
        return Result.fail("内部发生RuntimeException错误");
    }

    @ExceptionHandler
    public Result handler(RegisterException e) {
        log.error("捕获异常, e:", e);
        return Result.fail(e.getMessage());
    }
}
