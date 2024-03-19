package com.hlb.demo;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @BelongsProject: captcha_demo
 * @BelongsPackage: com.hlb.demo
 * @CreateTime : 2024/3/19 21:13
 * @Description: TODO
 * @Author: code_hlb
 */
@RestController
@RequestMapping("/admin")
public class KaptchaController {
    // kaptcha插件默认把验证码存储在Session⾥
    private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";
    private static final String KAPTCHA_SESSION_DATE = "KAPTCHA_SESSION_DATE";
    // 设置验证码的有效时间为 1min
    private static final long TIME_OUT = 60*1000;
    @RequestMapping("/check")
    public boolean check(String inputCaptcha, HttpSession session) {
        // 1、验证输入的验证码是否为空
        if (!StringUtils.hasLength(inputCaptcha)) {
            return false;
        }
        // 2、从session中获取正确的验证码
        String rightCaptcha = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        Date oldDate = (Date) session.getAttribute(KAPTCHA_SESSION_DATE);
        // 3、比对验证码(忽略大小写)
        if (inputCaptcha.equalsIgnoreCase(rightCaptcha)) {
            // 4、确认是否过期
            if (oldDate != null && (System.currentTimeMillis() - oldDate.getTime()) < TIME_OUT) {
                return true;
            }
        }
        System.out.println("right: " + rightCaptcha);
        System.out.println("input: " + inputCaptcha);
        return false;
    }
}
