package com.hlb.mybatis.model;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.model
 * @CreateTime : 2024/3/22 11:10
 * @Description: 数据库 user_info表对应的实体类
 * @Author: code_hlb
 */
@Data
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
