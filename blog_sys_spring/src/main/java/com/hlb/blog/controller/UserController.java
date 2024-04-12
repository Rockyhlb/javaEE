package com.hlb.blog.controller;

import com.hlb.blog.constants.Constant;
import com.hlb.blog.model.Result;
import com.hlb.blog.model.UserInfo;
import com.hlb.blog.service.UserService;
import com.hlb.blog.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.controller
 * @CreateTime : 2024/4/12 14:15
 * @Description: UserController
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(String userName, String password) {
        // 1、校验参数
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return Result.fail("用户名或密码不能为空~~");
        }
        // 2、根据用户名查询用户信息
        UserInfo userInfo = userService.queryUserByName(userName);
        if (userInfo == null || userInfo.getId() <= 0) {
            return Result.fail("用户不存在，快去注册吧~");
        }
        if (!password.equals(userInfo.getPassword())) {
            return Result.fail("账号名与密码不匹配，请重新输入~");
        }
        // 3、验证通过，生成token
        Map<String, Object> claim = new HashMap<>();
        claim.put(Constant.USER_CLAIM_ID, userInfo.getId());
        claim.put(Constant.USER_CLAIM_USER_NAME, userInfo.getUserName());
        return Result.success(JWTUtils.genToken(claim));
    }
}
