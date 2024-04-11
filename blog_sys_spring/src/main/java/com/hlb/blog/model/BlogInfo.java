package com.hlb.blog.model;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.model
 * @CreateTime : 2024/4/11 14:30
 * @Description: BlogInfo
 * @Author: code_hlb
 */
@Data
public class BlogInfo {
    private Integer id;
    private String title;
    private String content;
    private String userId;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
