package com.hlb.service;

import com.hlb.enums.BookStatusEnum;
import com.hlb.mapper.BookMapper;
import com.hlb.model.BookInfo;
import com.hlb.model.PageRequest;
import com.hlb.model.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.service
 * @CreateTime : 2024/3/26 9:45
 * @Description: 业务逻辑层：处理具体的业务逻辑
 * @Author: code_hlb
 */
@Service
@Slf4j
public class BookService {
    /**
     * 根据数据层返回的结果，对数据进行处理
     */
    @Autowired
    private BookMapper bookMapper;

    public String addBook(BookInfo bookInfo) {
        try {
            bookMapper.insertBook(bookInfo);
            return "";
        } catch (Exception e) {
            log.error("添加失败");
            return e.getMessage();
        }
    }

    public PageResult<BookInfo> getListByPage(PageRequest pageRequest) {
        Integer count = bookMapper.countBooks();
        List<BookInfo> bookInfos = bookMapper.queryBooksByPage(pageRequest);
        // 采用枚举类型完成 状态码 到 描述 之间的转换
        for (BookInfo book : bookInfos) {
            book.setStatusCN(BookStatusEnum.getMessageByCode(book.getStatus()).getMessage());
        }
        return new PageResult<>(count, bookInfos, pageRequest);
    }

    public BookInfo queryBookById(Integer bookId) {
        return bookMapper.queryBookById(bookId);
    }

    public Integer countBooks() {
        try {
            return bookMapper.countBooks();
        } catch (Exception e) {
            log.error("查找图书数量失败, e: ", e);
            return -1;
        }
    }

    public String updateBook(BookInfo bookInfo) {
        try {
            Integer res = bookMapper.updateBook(bookInfo);
            if (res <= 0) {
                return "更新失败..";
            }
            return "";
        } catch (Exception e) {
            log.error("更新图书失败, e: ", e);
            return e.getMessage();
        }
    }

    public Integer batchDelete(List<Integer> ids) {
        return bookMapper.batchDelete(ids);
    }
}
