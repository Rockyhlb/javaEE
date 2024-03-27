package com.hlb.enums;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.enums
 * @CreateTime : 2024/3/27 8:51
 * @Description: 业务状态码的枚举
 * @Author: code_hlb
 */
public enum ResultStatus {
    // 成功响应
    SUCCESS(200),
    // 未登陆
    UNLOGIN(-1),
    // 响应失败
    FAILED(-2);
    private Integer code;

    ResultStatus(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
