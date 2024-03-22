package com.hlb.mybatis.service;

import com.hlb.mybatis.mapper.UserInfoMapper;
import com.hlb.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.service
 * @CreateTime : 2024/3/22 16:32
 * @Description: 业务逻辑层
 * @Author: code_hlb
 */
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public List<UserInfo> queryAllUser() {
        return userInfoMapper.queryAllUser();
    }
}
