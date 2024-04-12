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

    public String getCreateTime() {
        return DateUtils.formatDate(this.createTime);
    }

    public String getUpdateTime() {
        return DateUtils.formatDate(updateTime);
    }
}
