package com.hlb.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.model
 * @CreateTime : 2024/3/25 11:01
 * @Description: 图书表
 * @Author: code_hlb
 */
@Data
public class BookInfo {
    // 图书ID
    private Integer id;
    // 书名
    private String bookName;
    // 作者
    private String author;
    // 数量
    private Integer count;
    // 价格
    private BigDecimal price;
    // 出版社
    private String publish;
    // 图书状态  -->  0: 无效码  1：可借阅  2: 不可借阅
    private Integer status;
    // 根据状态码，转述成中文
    private String statusCN;
    private Date createTime;
    private Date updateTime;
}
