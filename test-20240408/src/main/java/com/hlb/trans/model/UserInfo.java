package com.hlb.trans.model;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.model
 * @CreateTime : 2024/4/8 13:33
 * @Description: 用户表实体类
 * @Author: code_hlb
 */
@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private Date createTime;
    private Date updateTime;
}
