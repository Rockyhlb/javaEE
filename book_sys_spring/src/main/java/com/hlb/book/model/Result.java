package com.hlb.book.model;

import com.hlb.book.enums.ResultStatus;
import lombok.Data;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.model
 * @CreateTime : 2024/3/27 8:47
 * @Description: 对 PageResult进行封装，返回结果中包含业务状态码及状态信息
 * @Author: code_hlb
 */
@Data
public class Result<T> {
    // 业务状态码，采用枚举类型
    private Integer code;
    private String errmsg;
    private T data;

    // 成功时
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    // 失败时
    public static <T> Result<T> fail(T data, String errmsg) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.FAILED.getCode());
        result.setData(data);
        result.setErrmsg(errmsg);
        return result;
    }

    public static <T> Result<T> fail(String errmsg) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.FAILED.getCode());
        result.setErrmsg(errmsg);
        return result;
    }

    // 未登陆时
    public static <T> Result<T> unLogin() {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.UNLOGIN.getCode());
        result.setErrmsg("用户未登陆..");
        return result;
    }

    public static <T> Result<T> noUser(String errmsg) {
        Result<T> result = new Result<>();
        // 当后端返回的result业务状态码为 1 时，说明没有当前用户
        result.setCode(ResultStatus.NOUSER.getCode());
        result.setErrmsg(errmsg);
        return result;
    }
}
