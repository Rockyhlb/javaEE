package com.hlb.blog.constants;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.constants
 * @CreateTime : 2024/4/11 15:32
 * @Description: 常量
 * @Author: code_hlb
 */
public class Constant {
    public static final String USER_SESSION_KEY = "user_session_key";
    // Kaptcha插件默认把验证码存储在Session⾥
    public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";
    public static final String CAPTCHA_SESSION_DATE = "CAPTCHA_SESSION_DATE";
    // 设置验证码的有效时间为 1min
    public static final long TIME_OUT = 60 * 1000;
}
