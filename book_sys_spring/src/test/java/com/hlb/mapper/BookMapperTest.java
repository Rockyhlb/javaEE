package com.hlb.mapper;

import com.hlb.model.BookInfo;
import com.hlb.model.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        log.info("Starting..................");
    }

    @AfterEach
    void tearDown() {
        log.info("Ending..................");
    }

    @Test
    void insertBook() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName("book1");
        bookInfo.setAuthor("author1");
        bookInfo.setCount(33);
        bookInfo.setPrice(BigDecimal.valueOf(44.32));
        bookInfo.setPublish("publish1");
        bookInfo.setStatus(1);
        log.info(bookMapper.insertBook(bookInfo).toString());
    }

    @Test
    void queryBooksByPage() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setCurrentPage(2);
        pageRequest.setPageSize(10);
        log.info(bookMapper.queryBooksByPage(pageRequest).toString());
    }

    @Test
    void countBooks() {
        log.info(bookMapper.countBooks().toString());
    }

    @Test
    void queryBookById() {
        log.info(bookMapper.queryBookById(1).toString());
    }

    @Test
    void updateBook() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(25);
        bookInfo.setAuthor("book25");
        bookInfo.setCount(99);
        log.info(bookMapper.updateBook(bookInfo).toString());
    }

    @Test
    void batchDelete() {
        List<Integer> list = new ArrayList<>();
        list.add(26);
        list.add(25);
        list.add(24);
        log.info(bookMapper.batchDelete(list).toString());
    }
}