package com.hlb.controller;

import com.hlb.model.*;
import com.hlb.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.controller
 * @CreateTime : 2024/3/26 10:04
 * @Description: 表现层：图书
 * @Author: code_hlb
 */
@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {
    @Autowired // 取对象
    private BookService bookService;

    /*
     * 前后端交互接口：
     * type: Get
     * URL:  /book/getListByPage?currentPage=1
     * 参数： PageRequest pageRequest
     * 返回： PageResult<BookInfo>
     * */
    @RequestMapping("/getListByPage")
    public Result<PageResult<BookInfo>> getListByPage(PageRequest pageRequest) {
        // 返回列表
        log.info("分页查询：pageRequest:{}", pageRequest);
        if (pageRequest.getCurrentPage() < 1) {
            return Result.fail("非法参数..");
        }
        PageResult<BookInfo> pageResult = bookService.getListByPage(pageRequest);
        return Result.success(pageResult);
    }

    /*
     * 添加图书
     * 前后端交口：
     * Type: post
     * URL: /book/addBook
     * 参数：BookInfo
     * 返回：String 添加成功则返回空字符串
     * */
    @RequestMapping("/addBook")
    public String addBook(BookInfo bookInfo) {
        log.info("添加图书:{}", bookInfo);
        // 参数校验
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount() == null
                || bookInfo.getPrice() == null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus() == null
        ) {
            return "图书参数有误，请检查参数...";
        }
        return bookService.addBook(bookInfo);
    }

    /*
     * 进入修改页面，需要先根据URL中的bookId查询当前书籍的信息
     * 前后端交口：
     * Type: post
     * URL: /book/queryBookById
     * 参数：bookId
     * 返回：BookInfo
     * */
    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId) {
        log.info("查询图书, bookId:{}", bookId);
        // 校验参数
        Integer count = bookService.countBooks();
        if (bookId == null || bookId < 1 || bookId > count) {
            log.error("非法ID,bookId:{}", bookId);
            return null;
        }
        return bookService.queryBookById(bookId);
    }

    /*
     * 更新图书/删除图书(逻辑删除：将status=0)：
     * 前后端交口：
     * Type: post
     * URL: /book/updateBook
     * 参数：BookInfo
     * 返回：String
     * */
    @RequestMapping("/updateBook")
    public String updateBook(BookInfo bookInfo) {
        log.info("更新图书, book:{}", bookInfo);
        // 校验参数
        if (bookInfo.getId() < 0) {
            return "图书Id不合法..";
        }
        return bookService.updateBook(bookInfo);
    }

    /*
     * 批量删除图书(逻辑删除：将status=0)：
     * 前后端交口：
     * Type: post
     * URL: /book/batchDelete
     * 参数：List<Integer>
     * 返回：Boolean
     * */
    @RequestMapping("/batchDelete")
    // IllegalStateException: No primary or single unique constructor found for interface java.util.List
    // 原因：Spring默认在请求中参数名相同的多个值，是封装到数组，或者传入空参数，List是一个接口，并没有构造函数，因此无法实例化
    // 解决方法：使用 @RequestParam 封装到集合
    public boolean batchDelete(@RequestParam List<Integer> ids) {
        log.info("批量删除，ids={}", ids);
        try {
            Integer res = bookService.batchDelete(ids);
            return res > 0;
        } catch (Exception e) {
            log.error("批量删除数据失败,ids:{},e:{}", ids, e);
            return false;
        }
    }
}
