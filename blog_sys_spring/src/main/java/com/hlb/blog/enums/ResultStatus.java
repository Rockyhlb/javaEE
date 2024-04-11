package com.hlb.blog.enums;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.enums
 * @CreateTime : 2024/4/11 14:44
 * @Description: TODO
 * @Author: code_hlb
 */
public enum ResultStatus {
    SUCCESS(200),
    FAILED(-1),
    UNLOGIN(-2);
    private Integer code;

    ResultStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
