package com.hlb.book.constants;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.constants
 * @CreateTime : 2024/3/27 9:00
 * @Description: 存储常量
 * @Author: code_hlb
 */
public class Constants {
    // 将各个类使用的常量都提取出来封装到这个类中
    public static final String USER_SESSION_KEY = "user_session_key";

    // Kaptcha插件默认把验证码存储在Session⾥
    public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";
    public static final String CAPTCHA_SESSION_DATE = "CAPTCHA_SESSION_DATE";
    // 设置验证码的有效时间为 1min
    public static final long TIME_OUT = 60 * 1000;
}
