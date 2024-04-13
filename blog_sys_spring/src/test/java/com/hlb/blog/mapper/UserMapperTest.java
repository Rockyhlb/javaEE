package com.hlb.blog.mapper;

import com.hlb.blog.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void queryUserByName() {
        log.info(userMapper.queryUserByName("rocky").toString());
    }

    @Test
    void queryUserById() {
        log.info(userMapper.queryUserById(2).toString());
    }

    @Test
    void updateUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("admin");
        userInfo.setPassword("00000");
        log.info(userMapper.updateUser(userInfo).toString());
    }
}