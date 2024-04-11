package com.hlb.blog.model;

import com.hlb.blog.enums.ResultStatus;
import lombok.Data;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.model
 * @CreateTime : 2024/4/11 14:32
 * @Description: 统一返回结果格式
 * @Author: code_hlb
 */
@Data
public class Result<T> {
    private Integer code;  // 200: 成功   -1：失败  -2：未登录
    private String errMsg;
    private T Data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.SUCCESS.getCode());
        result.setErrMsg("");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String errMsg) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.FAILED.getCode());
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> fail(String errMsg, T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.FAILED.getCode());
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> unLogin(String errMsg) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.UNLOGIN.getCode());
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }
}
