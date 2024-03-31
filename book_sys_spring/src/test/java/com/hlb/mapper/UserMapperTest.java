package com.hlb.mapper;

import com.hlb.book.mapper.UserMapper;
import com.hlb.book.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        log.info("Starting.............");
    }

    @AfterEach
    void tearDown() {
        log.info("Ending.............");
    }

    @Test
    void queryByUserName() {
        log.info(userMapper.queryByUserName("admin").toString());
    }

    @Test
    void register() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("wangwu");
        userInfo.setPassword("098776");
        userInfo.setEmail("787928@qq.com");
        log.info(userMapper.register(userInfo).toString());
    }
}