package com.hlb.controller;

import com.hlb.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: book_sys
 * @BelongsPackage: com.hlb.book_sys
 * @CreateTime : 2024/3/13 10:04
 * @Description: TODO
 * @Author: code_hlb
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // 定义前后端交互接口：
    // 登陆接口：
    // type: post
    // URL:  /user/login
    // 参数： userName,passWord
    // 返回： true/false
    @RequestMapping("/login")
    public boolean login(String userName, String passWord, HttpSession session) {
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(passWord)) {
            return false;
        }
        if (new UserService().checkLogin(userName,passWord)) {
            session.setAttribute("curUser",userName);
            return true;
        }
        return false;
    }
}
