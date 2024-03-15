package com.hlb.dao;

import com.hlb.model.BookInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @BelongsProject: book_sys
 * @BelongsPackage: com.hlb.dao
 * @CreateTime : 2024/3/13 15:18
 * @Description: 数据访问层：也称为持久层，负责数据的访问操作，包括数据的增、删、查、改
 * @Author: code_hlb
 */
@Component
public class BookDao {
    public List<BookInfo> mockBookData() {
        // 生成暂时的测试数据，后续优化成访问访问数据库
        List<BookInfo> bookInfos = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(i);
            bookInfo.setBookName("图书" + i);
            bookInfo.setAuthor("作者"+ i);
            bookInfo.setCount(i*10+30);
//            Random random = new Random();
//            System.out.println(random.nextInt(1000));
            bookInfo.setPrice(BigDecimal.valueOf(new Random().nextInt(100)));
            bookInfo.setPublish("出版社"+i);
            bookInfo.setStatus(i%2==0?0:1);
            bookInfos.add(bookInfo);
        }
        return bookInfos;
    }
}
