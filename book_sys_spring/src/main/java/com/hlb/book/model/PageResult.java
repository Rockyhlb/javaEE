package com.hlb.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.model
 * @CreateTime : 2024/3/26 10:19
 * @Description: 接收 分页查询返回的数据
 * @Author: code_hlb
 */
// 添加无参全参构造器
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> {
    // 当前页的记录数
    private Integer total;
    // 当前页的记录, 采用泛型类保持对扩展开放原则
    // 开闭原则：对扩展开放，对修改关闭
    private List<T> records;
    // 将request一起打包返回给前端，前端调用request中的currentPage 和 pageSize
    private PageRequest pageRequest;
}
