package com.hlb.projects;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: test-20240312
 * @BelongsPackage: com.hlb.projects
 * @CreateTime : 2024/3/12 12:00
 * @Description: TODO
 * @Author: code_hlb
 */
@RequestMapping("/login")
@RestController
public class LoginController {
    // 接口定义：
    // 用户登陆验证                 检查当前登陆用户
    // URL: /login/check         /login/curUser
    // 参数：userName, passWord   无
    // 响应：true, false          当前登陆的用户名
    @RequestMapping("check")
    public boolean check(String userName,String passWord,HttpSession session) {
//        // 检查获取到的账号和密码是否为空
//        if (userName == null || "".equals(userName) || passWord == null || "".equals(passWord)){
//            return false;
//        }
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(passWord)) {
            return false;
        }

        // 检验账号密码是否正确，此处为了简便将账号密码分别设置为为“zhangsan:0000”
        if ("zhangsan".equals(userName) && "0000".equals(passWord)) {
            session.setAttribute("userName",userName);
            return true;
        }
        return false;
    }

    @RequestMapping("/curUser")
    public String curUser(HttpSession session) {
        String curUser = (String) session.getAttribute("userName");
        return curUser;
    }
}
