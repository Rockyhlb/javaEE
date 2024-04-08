package com.hlb.trans.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @BeforeEach
    void setUp() {
        log.info("开始测试.......");
    }

    @AfterEach
    void tearDown() {
        log.info("结束测试.......");
    }

    @Test
    void insertUser() {
        userInfoMapper.insertUser("张三","2222");
    }
}