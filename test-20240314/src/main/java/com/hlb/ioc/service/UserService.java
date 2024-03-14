package com.hlb.ioc.service;

import org.springframework.stereotype.Service;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.service
 * @CreateTime : 2024/3/14 12:26
 * @Description: 类注解：@Service
 * @Author: code_hlb
 */
@Service("userService1")
public class UserService {
    public void welcome() {
        System.out.println("hello,UserService!");
    }
}
