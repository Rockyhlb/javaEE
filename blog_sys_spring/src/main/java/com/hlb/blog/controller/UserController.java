package com.hlb.blog.controller;

import com.hlb.blog.constants.Constant;
import com.hlb.blog.enums.CaptchaStatusEnum;
import com.hlb.blog.model.Result;
import com.hlb.blog.model.UserInfo;
import com.hlb.blog.service.UserService;
import com.hlb.blog.utils.JWTUtils;
import com.hlb.blog.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.controller
 * @CreateTime : 2024/4/12 14:15
 * @Description: UserController
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(String userName, String password) {
        log.info("进入login,接收到参数userName:{},password:{}", userName, password);
        // 1、校验参数
        if (!StringUtils.hasLength(userName.trim()) || !StringUtils.hasLength(password.trim())) {
            log.error("用户名或密码为空");
            return Result.fail("用户名或密码不能为空~~");
        }
        // 2、根据用户名查询用户信息
        UserInfo userInfo = userService.queryUserByName(userName);
        if (userInfo == null || userInfo.getId() <= 0) {
            log.error("用户不存在");
            return Result.fail("用户不存在，快去注册吧~");
        }
        // 验证密码
        if (!SecurityUtils.verify(userInfo.getPassword(), password)) {
            log.error("账号名与密码不匹配");
            return Result.fail("账号名与密码不匹配，请重新输入~");
        }

        // 3、验证通过，生成token
        Map<String, Object> claim = new HashMap<>();
        claim.put(Constant.USER_CLAIM_ID, userInfo.getId());
        claim.put(Constant.USER_CLAIM_USER_NAME, userInfo.getUserName());
        String token = JWTUtils.genToken(claim);
        log.info("生成token,token:{}", token);
        return Result.success(token);
    }

    @RequestMapping("/register")
    public Result<Object> register(UserInfo userInfo, String inputCaptcha, HttpSession session) {
        log.info("注册用户：userInfo:{}, inputCaptcha:{}", userInfo, inputCaptcha);
        // 对验证码进行校验
        Result<Object> checkInputStatus = checkInput(userInfo, inputCaptcha, session);
        if (checkInputStatus.getCode() != 200) {
            return checkInputStatus;
        }
        // 最后调用service层代码
        return userService.register(userInfo);
    }

    private Result<Object> checkInput(UserInfo userInfo, String inputCaptcha, HttpSession session) {
        log.info("验证码校验，inputCaptcha:{}", inputCaptcha);
        // 1、校验输入
        if (userInfo.getUserName() == null || userInfo.getPassword() == null || userInfo.getGithubUrl() == null) {
            return Result.fail("请完成必要信息的填写...");
        }
        if (!StringUtils.hasLength(inputCaptcha)) {
            return Result.fail(CaptchaStatusEnum.NULL.getMsg());
        }
        // 2、从session中获取正确的验证码 和 验证码生成的时间
        String rightCaptcha = (String) session.getAttribute(Constant.CAPTCHA_SESSION_KEY);
        Date oldDate = (Date) session.getAttribute(Constant.CAPTCHA_SESSION_DATE);
        // 3、比对验证码(忽略大小写)
        if (inputCaptcha.equalsIgnoreCase(rightCaptcha)) {
            // 4、确认验证码是否过期
            if (oldDate != null && (System.currentTimeMillis() - oldDate.getTime()) < Constant.TIME_OUT) {
                return Result.success(CaptchaStatusEnum.SUCCESS.getCode());
            }
            return Result.fail(CaptchaStatusEnum.TIMEOUT.getMsg());
        }
        return Result.fail(CaptchaStatusEnum.FAILED.getMsg());
    }

    @RequestMapping("/updateUser")
    public Result<Object> updateUser(UserInfo userInfo, String inputCaptcha, HttpSession session) {
        log.info("注册用户：userInfo:{}, inputCaptcha:{}", userInfo, inputCaptcha);
        // 对验证码进行校验
        Result<Object> checkInputStatus = checkInput(userInfo, inputCaptcha, session);
        if (checkInputStatus.getCode() != 200) {
            return checkInputStatus;
        }
        // 3、最后调用service层代码
        return userService.updateUser(userInfo);
    }

    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(HttpServletRequest request) {
        log.info("进入getUserInfo");
        // 1、从请求头中获取token
        String token = request.getHeader(Constant.USER_TOKEN_HEADER);
        // 2、从token中解析出ID
        Integer userId = JWTUtils.getUserIdFromToken(token);
        if (userId == null || userId <= 0) {
            log.error("token解析出错");
            return null;
        }
        // 3、进入service
        UserInfo userInfo = userService.queryUserByID(userId);
        userInfo.setPassword("");
        log.info("user:{}", userInfo.getUserName());
        return userInfo;
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfo getAuthorInfo(Integer blogId) {
        log.info("进入getAuthorInfo,blogID:{}", blogId);
        // 校验参数
        if (blogId == null || blogId <= 0) {
            return null;
        }
        UserInfo authorInfo = userService.getAuthorInfoByBlogId(blogId);
        if (authorInfo == null) {
            return null;
        }
        authorInfo.setPassword("");
        log.info("user:{}", authorInfo.getUserName());
        return authorInfo;
    }

    @RequestMapping("/judgeSameUser")
    public Boolean judgeSameUser(HttpServletRequest request, Integer blogId) {
        log.info("judgeSameUser,blogId: " + blogId);
        // 1、获取token中的id
        String token = request.getHeader(Constant.USER_TOKEN_HEADER);
        Integer loginUserId = JWTUtils.getUserIdFromToken(token);
        if (loginUserId == null || loginUserId <= 0) {
            return false;
        }
        // 2、获取博主id
        Integer authorId = userService.getAuthorInfoByBlogId(blogId).getId();
        log.info("loginUserId:{}, authorId:{}", loginUserId, authorId);
        return loginUserId.equals(authorId);
    }
}
