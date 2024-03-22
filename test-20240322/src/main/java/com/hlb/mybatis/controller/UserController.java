package com.hlb.mybatis.controller;

import com.hlb.mybatis.model.UserInfo;
import com.hlb.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.controller
 * @CreateTime : 2024/3/22 16:31
 * @Description: 表现层
 * @Author: code_hlb
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryAllUser")
    public List<UserInfo> queryAllUser() {
        return userService.queryAllUser();
    }
}
