package com.hlb.blog.constants;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.constants
 * @CreateTime : 2024/4/11 15:32
 * @Description: 常量
 * @Author: code_hlb
 */
public class Constant {
    // Kaptcha 插件默认把验证码存储在Session⾥
    public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";
    public static final String CAPTCHA_SESSION_DATE = "CAPTCHA_SESSION_DATE";
    // 设置验证码的有效时间为 1min
    public static final long TIME_OUT = 60 * 1000;

    // 存储在客户端请求头中的 token 名称
    public static final String USER_TOKEN_HEADER = "user_token_header";
    // token 中存储的用户 Id
    public static final String USER_CLAIM_ID = "id";
    // token 中存储的用户 name
    public static final String USER_CLAIM_USER_NAME = "name";
}
