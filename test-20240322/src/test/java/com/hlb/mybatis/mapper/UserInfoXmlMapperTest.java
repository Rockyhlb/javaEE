package com.hlb.mybatis.mapper;

import com.hlb.mybatis.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@Slf4j
class UserInfoXmlMapperTest {
    @Autowired
    private UserInfoXmlMapper userInfoXmlMapper;

    @BeforeEach
    void setUp() {
        log.info("Start.......................................");
    }

    @AfterEach
    void tearDown() {
        log.info("Ending.......................................");
    }

    @Test
    void queryAllUser() {
        log.info(userInfoXmlMapper.queryAllUser().toString());
    }

    @Test
    void queryAllUser1() {
        log.info(userInfoXmlMapper.queryAllUser1().toString());
    }

    @Test
    void queryUserByParam() {
        log.info(userInfoXmlMapper.queryUserByParam(5,0).toString());
    }

    @Test
    void insertByOb() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("123456");
        userInfo.setAge(20);
        userInfo.setGender(0);
        userInfo.setPhone("1988888888");
        Integer count = userInfoXmlMapper.insertByOb(userInfo);
        log.info("受影响的行数：" + count + ", 用户Id: " + userInfo.getId());
    }

    @Test
    void deleteByUserId() {
        Integer count = userInfoXmlMapper.deleteByUserId(7);
        log.info("删除：" + count + " row..");
    }

    @Test
    void updateByUserId() {
        Integer count = userInfoXmlMapper.updateByUserId("234123",new Date(),2);
        log.info("更新：" + count + " 行..");
    }

    @Test
    void updateByUserOb() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(5);
        userInfo.setUsername("tianqi");
        userInfo.setPassword("654321");
        userInfo.setUpdateTime(new Date());
        Integer count = userInfoXmlMapper.updateByUserOb(userInfo);
        log.info("更新：" + count + " row，用户ID: " + userInfo.getId());
    }
}