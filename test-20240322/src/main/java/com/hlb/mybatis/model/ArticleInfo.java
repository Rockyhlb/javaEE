package com.hlb.mybatis.model;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.model
 * @CreateTime : 2024/3/24 20:52
 * @Description: article_info 的对应实体类
 * @Author: code_hlb
 */
@Data
public class ArticleInfo {
    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;

    // 存储多表查询得到的用户信息
    private String username;
    private Integer age;
    private Integer gender;
}
