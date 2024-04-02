package com.hlb.aop.component;

import org.springframework.stereotype.Component;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.component
 * @CreateTime : 2024/4/2 19:10
 * @Description: 没有实现接口的普通类
 * @Author: code_hlb
 */
@Component
public class UserComponent2 {
    public void sayHi() {
        System.out.println("UserComponent2 No Implements...");
    }
}
