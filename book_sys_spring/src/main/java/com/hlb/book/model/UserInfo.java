package com.hlb.book.model;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.model
 * @CreateTime : 2024/3/25 16:11
 * @Description: 用户表
 * @Author: code_hlb
 */
@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
