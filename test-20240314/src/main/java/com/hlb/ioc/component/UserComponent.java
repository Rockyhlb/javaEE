package com.hlb.ioc.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.component
 * @CreateTime : 2024/3/14 11:45
 * @Description: 类注解：@Component
 * @Author: code_hlb
 */
@Component   // 存对象
public class UserComponent {
    // 取对象
    @Autowired
    public void welcome() {
        System.out.println("hello,UserComponent!");
    }
}
