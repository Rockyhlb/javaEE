package com.hlb.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @BelongsProject: book_sys
 * @BelongsPackage: com.hlb.book_sys
 * @CreateTime : 2024/3/13 11:01
 * @Description: 存储图书信息
 * @Author: code_hlb
 */
@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    private BigDecimal price;
    // 出版社
    private String publish;
    // 图书状态
    // 0: 可借阅  1：不可借阅
    private Integer status;
    // 根据状态码，中文表达状态
    private String stateCN;
}
