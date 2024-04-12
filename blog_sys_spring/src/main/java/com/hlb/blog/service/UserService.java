package com.hlb.blog.service;

import com.hlb.blog.mapper.UserMapper;
import com.hlb.blog.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.service
 * @CreateTime : 2024/4/12 16:11
 * @Description: UserService
 * @Author: code_hlb
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserInfo queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }
}
