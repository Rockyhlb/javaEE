package com.hlb.controller;

import com.hlb.constants.Constants;
import com.hlb.enums.CaptchaStatusEnum;
import com.hlb.model.Result;
import com.hlb.model.UserInfo;
import com.hlb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.controller
 * @CreateTime : 2024/3/26 10:04
 * @Description: 表现出：用户
 * @Author: code_hlb
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
     * 定义前后端交互接口：
     * 登陆接口：
     * type: post
     * URL:  /user/login
     * 参数： userName,passWord
     * 返回： true/false
     * */
    @RequestMapping("/login")
    public boolean login(String userName, String passWord, HttpSession session) {
        log.info("登陆验证：username:{}", userName);
        // 校验输入是否为空
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(passWord)) {
            return false;
        }
        // 调用业务逻辑层验证账户密码是否正确，并返回查询到的用户对象
        UserInfo userInfo = userService.checkLogin(userName, passWord);
        if (userInfo != null) {
            // 创建会话
            userInfo.setPassword("");
            session.setAttribute(Constants.USER_SESSION_KEY, userInfo);
            return true;
        }
        return false;
    }

    private CaptchaStatusEnum check(String inputCaptcha, HttpSession session) {
        log.info("验证码校验，inputCaptcha:{}", inputCaptcha);
        // 1、验证输入的验证码是否为空
        if (!StringUtils.hasLength(inputCaptcha)) {
            return CaptchaStatusEnum.NULL;
        }
        // 2、从session中获取正确的验证码 和 验证码生成的时间
        String rightCaptcha = (String) session.getAttribute(Constants.CAPTCHA_SESSION_KEY);
        Date oldDate = (Date) session.getAttribute(Constants.CAPTCHA_SESSION_DATE);
        // 3、比对验证码(忽略大小写)
        if (inputCaptcha.equalsIgnoreCase(rightCaptcha)) {
            // 4、确认是否过期
            if (oldDate != null && (System.currentTimeMillis() - oldDate.getTime()) < Constants.TIME_OUT) {
                return CaptchaStatusEnum.SUCCESS;
            }
            return CaptchaStatusEnum.TIMEOUT;
        }
        return CaptchaStatusEnum.FAILED;
    }

    /*
     * 注册用户接口：
     * type: post
     * URL:  /user/register
     * 参数： UserInfo, String inputCaptcha
     * 返回： Result
     * */
    @RequestMapping("/register")
    public Result<UserInfo> register(UserInfo userInfo, String inputCaptcha, HttpSession httpSession) {
        log.info("注册用户：userInfo:{}, inputCaptcha:{}", userInfo, inputCaptcha);
        // 1、先校验输入是否为空
        if (userInfo.getUserName() == null ||
                userInfo.getPassword() == null ||
                userInfo.getEmail() == null ||
                inputCaptcha == null) {
            return Result.fail("请完成必要信息的填写...");
        }
        // 2、然后对验证码进行校验
        CaptchaStatusEnum captchaStatusEnum = check(inputCaptcha, httpSession);
        if (!Objects.equals(CaptchaStatusEnum.SUCCESS.getCode(), captchaStatusEnum.getCode())) {
            return Result.fail(captchaStatusEnum.getMsg());
        }
        // 3、最后调用service层代码
        return userService.register(userInfo);
    }

    @RequestMapping("/updateUser")
    public Result<UserInfo> update(UserInfo userInfo, String inputCaptcha, HttpSession session) {
        log.info("修改账户：userInfo:{}, inputCaptcha:{}", userInfo, inputCaptcha);
        // 1、先校验输入是否为空
        if (userInfo.getUserName() == null ||
                userInfo.getPassword() == null ||
                userInfo.getEmail() == null ||
                inputCaptcha == null) {
            return Result.fail("请完成必要信息的填写...");
        }
        // 2、然后对验证码进行校验
        CaptchaStatusEnum captchaStatusEnum = check(inputCaptcha, session);
        if (!Objects.equals(CaptchaStatusEnum.SUCCESS.getCode(), captchaStatusEnum.getCode())) {
            return Result.fail(captchaStatusEnum.getMsg());
        }
        // 3、最后调用service层代码
        return userService.updateUser(userInfo);
    }

    @RequestMapping("/logout")
    public boolean logout(HttpServletRequest httpServletRequest) {
        return userService.logout(httpServletRequest);
    }
}
