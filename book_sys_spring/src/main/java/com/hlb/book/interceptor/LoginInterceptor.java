package com.hlb.book.interceptor;

import com.hlb.book.constants.Constants;
import com.hlb.book.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.interceptor
 * @CreateTime : 2024/3/27 15:25
 * @Description: 定义拦截器（定义拦截内容）
 * @Author: code_hlb
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 拦截器是Spring框架提供的核⼼功能之⼀, 主要用来拦截用户的请求,
    // 在指定⽅法前后, 根据业务需要执行预先设定的代码
    // 自定义拦截器，需要实现HandlerInterceptor接口
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 通过拦截器拦截所有的请求，并进行Session校验
        log.info("preHandle..进行身份校验");
        // 验证登陆情况
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.USER_SESSION_KEY);
        if (userInfo == null || userInfo.getId() < 1 || "".equals(userInfo.getUserName())) {
            // 返回Http 状态码401: Unauthorized,未验证状态
            response.setStatus(401);
            return false;
        }
        // true：放行   false：拦截
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("postHandle...目标方法执行后执行");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("afterCompletion...视图渲染完毕后执行，最后执行");
//    }
}
