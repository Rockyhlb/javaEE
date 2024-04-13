package com.hlb.blog.model;

import com.hlb.blog.utils.DateUtils;
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
    private Integer userId;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;

    public BlogInfo() {
    }

    public BlogInfo(String title, String content, Integer userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public BlogInfo(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getCreateTime() {
        return DateUtils.formatDate(this.createTime);
    }

    public String getUpdateTime() {
        return DateUtils.formatDate(updateTime);
    }
}