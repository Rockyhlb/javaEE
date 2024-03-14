package com.hlb.ioc.controller;

import org.springframework.stereotype.Controller;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.controller
 * @CreateTime : 2024/3/14 12:20
 * @Description: 类注解：@Controller
 * @Author: code_hlb
 */
@Controller
public class UserController {
    public void welcome() {
        System.out.println("hello,UserController!");
    }
}
