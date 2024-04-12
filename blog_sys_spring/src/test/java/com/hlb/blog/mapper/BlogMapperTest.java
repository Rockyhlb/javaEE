package com.hlb.blog.mapper;

import com.hlb.blog.model.BlogInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class BlogMapperTest {
    @Autowired
    private BlogMapper blogMapper;

    @BeforeEach
    void setUp() {
        log.info("开始测试......");
    }

    @AfterEach
    void tearDown() {
        log.info("开始测试......");
    }

    @Test
    void queryBlogList() {
        log.info(blogMapper.queryBlogList().toString());
    }

    @Test
    void queryBlogById() {
        log.info(blogMapper.queryBlogById(2).toString());
    }

    @Test
    void insertBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("测试title1...");
        blogInfo.setContent("测试content1...");
        blogInfo.setUserId(1);
        log.info(blogMapper.insertBlog(blogInfo).toString());
    }

    @Test
    void updateBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("测试title1title1...");
        blogInfo.setContent("测试content1...");
        blogInfo.setId(1);
        log.info(blogMapper.updateBlog(blogInfo).toString());
    }

    @Test
    void updateBlog1() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(1);
        blogInfo.setDeleteFlag(1);
        log.info(blogMapper.updateBlog(blogInfo).toString());
    }


}