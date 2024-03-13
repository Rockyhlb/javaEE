package com.hlb.controller;

import com.hlb.model.BookInfo;
import com.hlb.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @BelongsProject: book_sys
 * @BelongsPackage: com.hlb
 * @CreateTime : 2024/3/13 10:58
 * @Description: 表现层：接收前端发送的请求，对请求进行处理，并响应数据
 * @Author: code_hlb
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @RequestMapping("/getList")
    public List<BookInfo> getBookInfoList() {
        // 定义前后端交互接口：
        // 获取列表：
        // type: Get
        // URL:  /book/getList
        // 参数： 无
        // 返回： List<BookInfo>
        BookService bookService = new BookService();
        // 从业务逻辑层中获取内存中的mock数据
        List<BookInfo> bookInfos = bookService.bookInfos();
        // 返回列表
        return bookInfos;
    }

}
