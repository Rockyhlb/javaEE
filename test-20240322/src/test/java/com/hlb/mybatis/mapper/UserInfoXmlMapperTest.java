package com.hlb.mybatis.mapper;

import com.hlb.mybatis.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

@SpringBootTest
@Slf4j
class UserInfoXmlMapperTest {
    @Autowired
    private UserInfoXmlMapper userInfoXmlMapper;

    @BeforeEach
    void setUp() {
        log.info("Starting.......................................");
    }

    @AfterEach
    void tearDown() {
        log.info("Ending.......................................");
    }

    // --> 查询
    @Test
    void queryUserList() {
        log.info(userInfoXmlMapper.queryUserList().toString());
    }

    @Test
    void queryUserList1() {
        log.info(userInfoXmlMapper.queryUserList1().toString());
    }

    @Test
    void queryUserByWhere() {
        log.info(userInfoXmlMapper.queryUserByWhere(null,null).toString());
    }

    // --> 添加
    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("lisi");
        userInfo.setAge(15);
        userInfo.setPassword("123456");
        userInfo.setGender(1);
        Integer count = userInfoXmlMapper.insert(userInfo);
        log.info("添加：" + count + " row，用户ID: " + userInfo.getId());
    }

    @Test
    void insert2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("lisi");
//        userInfo.setAge(15);
        userInfo.setPassword("123456");
//        userInfo.setGender(1);
//        userInfo.setPhone();
        Integer result = userInfoXmlMapper.insert2(userInfo);
        log.info("result:"+result+",id:"+userInfo.getId());
    }

    // --> 删除
    @Test
    void delete() {
        userInfoXmlMapper.delete(9);
    }

    @Test
    void batchDelete() {
        Integer count = userInfoXmlMapper.batchDelete(Arrays.asList(8,9,10,11));
        log.info("删除：" + count + " rows...");
    }

    // --> 更新
    @Test
    void update() {
        userInfoXmlMapper.update("admin",2,new Date());
    }

    @Test
    void update2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("tianqi");
//        userInfo.setAge(15);
        userInfo.setPassword("123456");
        // 根据用户ID修改消息
        userInfo.setId(5);
        userInfo.setUpdateTime(new Date());
        Integer count = userInfoXmlMapper.update1(userInfo);
        log.info("更新：" + count + " row，用户ID: " + userInfo.getId());
    }
}