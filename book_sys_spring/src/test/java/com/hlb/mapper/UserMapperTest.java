package com.hlb.mapper;

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
}