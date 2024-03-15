package com.hlb.controller;

import com.hlb.model.BookInfo;
import com.hlb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Component   // 存对象，让Spring对这个对象进行管理
@RequestMapping("/book")
public class BookController {
    @Autowired // 取对象
    private BookService bookService;
    @RequestMapping("/getList")
    public List<BookInfo> getBookInfoList() {
        // 定义前后端交互接口：
        // 获取列表：
        // type: Get
        // URL:  /book/getList
        // 参数： 无
        // 返回： List<BookInfo>
//        BookService bookService = new BookService();
        // 从业务逻辑层中获取内存中的mock数据
        List<BookInfo> bookInfos = bookService.bookInfos();
        // 返回列表
        return bookInfos;
    }

}
