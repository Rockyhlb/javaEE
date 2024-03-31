package com.hlb.book.config;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.config
 * @CreateTime : 2024/3/31 20:23
 * @Description: 自定义异常 -- 注册用户时，当出现用户名已存在的情况抛出该异常
 * @Author: code_hlb
 */
public class RegisterException extends Exception {
    public RegisterException() {
    }

    public RegisterException(String message) {
        super(message);
    }
}
