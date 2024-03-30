package com.hlb.service;

import com.hlb.constants.Constants;
import com.hlb.mapper.UserMapper;
import com.hlb.model.Result;
import com.hlb.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.service
 * @CreateTime : 2024/3/26 12:55
 * @Description: service: user
 * @Author: code_hlb
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserInfo checkLogin(String userName, String passWord) {
        // 从数据库中取数据进行验证
        UserInfo userInfo = userMapper.queryByUserName(userName);
        if (userInfo == null) {
            return null;
        }
        return passWord.equals(userInfo.getPassword()) ? userInfo : null;
    }

    public Result<UserInfo> register(UserInfo userInfo) {
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

    public Result<UserInfo> updateUser(UserInfo userInfo) {
        try {
            UserInfo oldUser = userMapper.queryByUserName(userInfo.getUserName());
            if (oldUser == null) {
                return Result.noUser("系统中无该用户，请先进行注册..");
            }
            if (userMapper.updateUser(userInfo, oldUser.getId()) > 0) {
                userInfo.setPassword("");
                return Result.success(userInfo);
            }
            return Result.fail("发生内部错误，请联系管理员...");
        } catch (Exception e) {
            log.error("更新失败,Exception: {}", e.getMessage());
            return Result.fail("更新失败..");
        }
    }

    public boolean logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        if (userInfo == null || userInfo.getId() < 1 || "".equals(userInfo.getUserName())) {
            return false;
        }
        // 采用移除 Session 中 Attribute 的方式退出登陆
        httpSession.removeAttribute(Constants.USER_SESSION_KEY);
        return true;
    }
}
