package com.hlb.service;

import org.springframework.stereotype.Service;

/**
 * @BelongsProject: book_sys
 * @BelongsPackage: com.hlb.service
 * @CreateTime : 2024/3/13 15:55
 * @Description: TODO
 * @Author: code_hlb
 */
@Service
public class UserService {
    public boolean checkLogin(String userName,String passWord) {
        // 假定当前用户名和密码是 {"zhangsan:"0000"},后续从数据库中取数据进行优化
        if ("zhangsan".equals(userName) && "0000".equals(passWord)) {
            return true;
        }
        return false;
    }
}
