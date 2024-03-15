package com.hlb.service;

import com.hlb.dao.BookDao;
import com.hlb.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @BelongsProject: book_sys
 * @BelongsPackage: com.hlb.service
 * @CreateTime : 2024/3/13 15:18
 * @Description: 业务逻辑层：处理具体的业务逻辑
 * @Author: code_hlb
 */
@Component
public class BookService {
    /**
     * 根据数据层返回的结果，对数据进行处理
     */
    @Autowired
    private BookDao bookDao;
    public List<BookInfo> bookInfos() {
//        BookDao bookDao = new BookDao();
        List<BookInfo> bookInfoList = bookDao.mockBookData();
        // 根据状态码转换状态
        for (BookInfo book: bookInfoList) {
            if (0 == book.getStatus()) {
                book.setStateCN("可借阅");
            }else {
                book.setStateCN("不可借阅");
            }
        }
        return bookInfoList;
    }
}
