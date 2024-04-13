package com.hlb.blog.enums;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.enums
 * @CreateTime : 2024/04/13 21:44
 * @Description: 状态码-验证
 * @Author: code_hlb
 */
public enum CaptchaStatusEnum {
    SUCCESS(200, "验证成功"),
    TIMEOUT(-1, "验证码过期"),
    NULL(-2, "验证码为空"),
    FAILED(-3, "验证码不匹配");
    private Integer code;
    private String msg;

    CaptchaStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
