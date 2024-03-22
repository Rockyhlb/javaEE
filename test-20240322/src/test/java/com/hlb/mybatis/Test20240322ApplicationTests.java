package com.hlb.mybatis;

import com.hlb.mybatis.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test20240322ApplicationTests {
    @Autowired
    private UserInfoMapper userInfoMapper; // 注入mapper
    @Test
    void contextLoads() {
//        System.out.println(userInfoMapper.queryAllUser().toString());
        userInfoMapper.queryAllUser().forEach(x -> System.out.println(x.toString()));
//        System.out.println(userInfoMapper.queryUserById(2));
    }
}
