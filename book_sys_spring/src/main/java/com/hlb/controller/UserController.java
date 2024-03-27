package com.hlb.controller;

import com.hlb.constants.Constants;
import com.hlb.model.UserInfo;
import com.hlb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.controller
 * @CreateTime : 2024/3/26 10:04
 * @Description: 表现出：用户
 * @Author: code_hlb
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    // 定义前后端交互接口：
    // 登陆接口：
    // type: post
    // URL:  /user/login
    // 参数： userName,passWord
    // 返回： true/false
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public boolean login(String userName, String passWord, HttpSession session) {
        log.info("登陆验证：username:{}",userName);
        // 校验输入是否为空
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(passWord)) {
            return false;
        }
        // 调用业务逻辑层验证账户密码是否正确，并返回查询到的用户对象
        UserInfo userInfo = userService.checkLogin(userName,passWord);
        if (userInfo != null) {
            // 创建会话
            userInfo.setPassword("");
            session.setAttribute(Constants.USER_SESSION_KEY,userInfo);
            return true;
        }
        return false;
    }
}
