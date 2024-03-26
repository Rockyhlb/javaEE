package com.hlb.model;

import lombok.Data;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.model
 * @CreateTime : 2024/3/26 10:00
 * @Description: 请求：分页查询
 * @Author: code_hlb
 */
@Data
public class PageRequest {
    // 记录当前页，默认为1
    private Integer currentPage = 1;
    // 记录当前页展示多少条数据，默认为10
    private Integer pageSize = 10;
    // 记录偏移量  --> 当前页从哪条数据开始查询
    private Integer offset;
    // 翻页查询：  select * from book_info where status <> 0 limit #{offset],#{pageSize}
    // 第一页数据：select * from book_info where status <> 0 limit 0,10
    // 第一页数据：select * from book_info where status <> 0 limit 10,10
    // 第一页数据：select * from book_info where status <> 0 limit 20,10
    // offset = (currentPage-1) * pageSize
    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
