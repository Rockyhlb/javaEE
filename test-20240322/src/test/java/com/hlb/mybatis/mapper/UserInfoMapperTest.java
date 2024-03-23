package com.hlb.mybatis.mapper;

import com.hlb.mybatis.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @BeforeEach
    void setUp() {
        // 执行下面 @Test 修饰的方法之前执行
        log.info("starting....");
    }

    @AfterEach
    void tearDown() {
        // 执行完下面 @Test 修饰的所有方法之后执行
        log.info("ending....");
    }

    @Test
    void queryAllUser() {
        log.info(userInfoMapper.queryAllUser().toString());
    }

    @Test
    void queryAllUser1() {
        log.info(userInfoMapper.queryAllUser1().toString());
    }

    @Test
    void queryAllUser2() {
        log.info(userInfoMapper.queryAllUser2().toString());
    }

    @Test
    void queryAllUser3() {
        log.info(userInfoMapper.queryAllUser3().toString());
    }

    @Test
    void queryAllUser4() {
        log.info(userInfoMapper.queryAllUser4().toString());
    }

    @Test
    void queryUserById() {
        UserInfo userInfo = userInfoMapper.queryUserById(2);
        log.info(userInfo.toString());
    }

    @Test
    void queryUserByParam() {
        System.out.println(userInfoMapper.queryUserByParam(4, 0).toString());
    }

    @Test
    void queryUserByParam1() {
        System.out.println(userInfoMapper.queryUserByParam1(4, 0).toString());
    }

    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("123456");
        userInfo.setAge(20);
        userInfo.setGender(0);
        userInfo.setPhone("1988888888");
        userInfoMapper.insert(userInfo);
    }

    @Test
    void insertByParam() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("123456");
        userInfo.setAge(20);
        userInfo.setGender(0);
        userInfo.setPhone("1988888888");
        Integer count = userInfoMapper.insertByParam(userInfo);
        log.info("受影响的行数：" + count + ", 用户Id: " + userInfo.getId());
    }

    @Test
    void deleteByUserId() {
        Integer count = userInfoMapper.deleteByUserId(7);
        log.info("删除：" + count + " row..");
    }

    @Test
    void updateByUserId() {
        Integer count = userInfoMapper.updateByUserId("234123",new Date(),2);
        log.info("更新：" + count + " 行..");
    }

    @Test
    void updateByUserOb() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(5);
        userInfo.setUsername("tianqi");
        userInfo.setPassword("654321");
        userInfo.setUpdateTime(new Date());
        Integer count = userInfoMapper.updateByUserOb(userInfo);
        log.info("更新：" + count + " row，用户ID: " + userInfo.getId());
    }
}