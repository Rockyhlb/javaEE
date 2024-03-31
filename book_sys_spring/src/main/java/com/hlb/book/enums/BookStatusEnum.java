package com.hlb.book.enums;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.enums
 * @CreateTime : 2024/3/26 11:21
 * @Description: 通过枚举类型处理 图书状态码 和 状态描述 之间的映射关系
 * @Author: code_hlb
 */
public enum BookStatusEnum {
    DELETED(0, "无效"),
    NORMAL(1, "可借阅"),
    FORBIDDEN(2, "不可借阅");

    private int code;
    private String message;

    BookStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 根据code 获取 message
    public static BookStatusEnum getMessageByCode(int code) {
        switch (code) {
            case 0:
                return BookStatusEnum.DELETED;
            case 1:
                return BookStatusEnum.NORMAL;
            case 2:
                return BookStatusEnum.FORBIDDEN;
        }
        return BookStatusEnum.DELETED;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
