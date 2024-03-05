package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: servlet
 * @CreateTime : 2024/3/5 10:09
 * @Description: 处理来自 退出登录 的Get请求
 * @Author: code_hlb
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理退出登陆的get请求
        // 移除session中的attribute(之前判定登陆状态的逻辑是同时判断session和attribute)
        // 为什么不直接删除session本身：因为servlet没有提供删除session的方法，
        // 虽然可以设置session的过期时间，但是并不优雅
        HttpSession session = req.getSession(false);
        if (session == null) {
            // 当前没有账号登陆
            resp.setContentType("text/html; charset = utf8");
            resp.getWriter().write("当前无用户登陆！");
            return;
        }
        // 移除session中的user属性
        session.removeAttribute("user");
        // 跳转到登录页
        resp.sendRedirect("login.html");
    }
}
