package com.hlb.blog.model;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.model
 * @CreateTime : 2024/4/11 14:28
 * @Description: UserInfo
 * @Author: code_hlb
 */
@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private String githubUrl;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
