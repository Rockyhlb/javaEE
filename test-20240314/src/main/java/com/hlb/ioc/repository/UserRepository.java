package com.hlb.ioc.repository;

import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.repository
 * @CreateTime : 2024/3/14 12:27
 * @Description: 类注解：@Repository
 * @Author: code_hlb
 */
@Repository
public class UserRepository {
    public void welcome() {
        System.out.println("hello,UserRepository!");
    }
}
