package com.hlb.mvc;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: test-20240312
 * @BelongsPackage: com.hlb.demo
 * @CreateTime : 2024/3/12 8:35
 * @Description: 从请求中获取信息
 * @Author: code_hlb
 */
@RequestMapping("/request")
@RestController
public class RequestController {
    @RequestMapping("/getCookies")
    public String getCookies(HttpServletRequest request) {
        // 传统方式获取Cookies,该种方式获取的是客户端所有Cookie
        Cookie[] cookies = request.getCookies();
        // Lambda 简洁写法
        // Arrays.stream(cookies).forEach(x -> System.out.println(x.getName() + ": " + x.getValue()));
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
            return "成功获取Cookie!";
        }
        return "Cookie 为空";
    }

    @RequestMapping("/getCookie")
    public String getCookie(@CookieValue("name") String userName) {
        // 通过注解只能获取单个Cookie
        return "userName: " + userName;
    }

    @RequestMapping("/setSession")
    public String setSession(HttpServletRequest request) {
        // 设置会话Session
        HttpSession session = request.getSession();
        session.setAttribute("userName","zhangsan");
        return "设置session成功！";
    }

    @RequestMapping("/getSession0")
    public String getSession0(HttpServletRequest request) {
        // 传统方式获取会话Session
        HttpSession session = request.getSession(); // 默认为true,没有会话时创建会话，避免空指针异常
        String userName = (String) session.getAttribute("userName");
        return "当前会话的userName: " + userName;
    }
    @RequestMapping("getSession1")
    public String getSession1(HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        return "当前会话的userName: " + userName;
    }
    @RequestMapping("/getSession2")
    public String getSession3(@SessionAttribute(value = "userName",required = false) String name) {
        // 通过注解的方式只能取到会话中的特定属性
        return "当前会话的userName: " + name;
    }

    @RequestMapping("/getHeader0")
    public String getHeader(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return "User-Agent: " + userAgent;
    }

    @RequestMapping("/getHeader1")
    public String getHeader1(@RequestHeader("User-Agent") String userAgent) {
        return "User-Agent: " + userAgent;
    }
}
