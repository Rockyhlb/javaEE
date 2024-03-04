package login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @BelongsProject: test-20240304
 * @BelongsPackage: login
 * @CreateTime : 2024/3/4 22:47
 * @Description: 登陆成功后的首页
 * @Author: code_hlb
 */
@WebServlet("/index")
public class indexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 先获取到当前用户对应的会话对象. 生成的页面要根据当前用户的会话信息来构造
        // req.getSession(false):参数为false,如果没有查到session，返回null
        HttpSession session = req.getSession(false);
        if (session == null) {
            // sessionId 不存在, 或 session 没有在 hash 表中查到.
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("您当前尚未登录!");
            return;
        }
        // 2. 从会话中拿到之前 session.setAttribute() 存储的用户信息
        //    此处的强转,是由于源码中：Object getAttribute(String var1) 返回的是Object类型
        String username = (String) session.getAttribute("username");
        Long loginTime = (Long) session.getAttribute("loginTime");
        // 3. 生成一个页面, 把上述数据显示到页面上.
        resp.setContentType("text/html; charset=utf8");
        String respBody = "欢迎你 " + username + "! 上次登录时间为: " + loginTime;
        resp.getWriter().write(respBody);
    }
}
