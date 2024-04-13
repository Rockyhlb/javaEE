package com.hlb.blog.service;

import com.hlb.blog.mapper.BlogMapper;
import com.hlb.blog.mapper.UserMapper;
import com.hlb.blog.model.BlogInfo;
import com.hlb.blog.model.Result;
import com.hlb.blog.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.service
 * @CreateTime : 2024/4/12 16:11
 * @Description: UserService
 * @Author: code_hlb
 */

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;

    public UserInfo queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }

    public UserInfo queryUserByID(Integer userId) {
        return userMapper.queryUserById(userId);
    }

    public UserInfo getAuthorInfoByBlogId(Integer blogId) {
        // 1、根据博客ID 获取 用户ID
        BlogInfo blogInfo = blogMapper.queryBlogById(blogId);
        if (blogInfo == null) {
            return null;
        }
        Integer userId = blogInfo.getUserId();
        if (userId == null || userId <= 0) {
            return null;
        }
        // 2、根据用户ID获取用户信息
        return userMapper.queryUserById(userId);
    }

    public Result<Object> register(UserInfo userInfo) {
        try {
            if (userMapper.register(userInfo) > 0) {
                userInfo.setPassword("");
                return Result.success(userInfo);
            }
            return Result.fail("注册失败，请检查输入是否有误..");
        } catch (Exception e) {
            log.error("注册失败, Exception: {}", e.getMessage());
            return Result.fail("当前用户名已被注册...");
        }
    }

    public Result<Object> updateUser(UserInfo userInfo) {
        try {
            UserInfo oldUser = userMapper.queryUserByName(userInfo.getUserName());
            if (oldUser == null) {
                return Result.fail("系统中无该用户，请先进行注册..");
            }
            // 根据用户名进行信息修改
            userInfo.setUpdateTime(new Date());
            if (userMapper.updateUser(userInfo) > 0) {
                userInfo.setPassword("");
                return Result.success(userInfo);
            }
            return Result.fail("发生内部错误，请联系管理员...");
        } catch (Exception e) {
            log.error("更新失败,Exception: {}", e.getMessage());
            return Result.fail("更新失败..");
        }
    }
}
