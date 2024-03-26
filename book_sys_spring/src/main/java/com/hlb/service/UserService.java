package com.hlb.service;

import com.hlb.mapper.UserMapper;
import com.hlb.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.service
 * @CreateTime : 2024/3/26 12:55
 * @Description: service: user
 * @Author: code_hlb
 */
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
//        if (passWord.equals(userInfo.getPassword())) {
//            return userInfo;
//        }
//        return null;
        return passWord.equals(userInfo.getPassword()) ? userInfo : null;
    }
}
