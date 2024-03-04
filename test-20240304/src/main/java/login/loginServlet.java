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
 * @CreateTime : 2024/3/4 22:46
 * @Description: 处理登陆请求
 * @Author: code_hlb
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
    // Cookie : 客户端存储数据的机制，存储用户身份标识(sessionId)
    // Session: 服务器端存储数据的机制,服务器端会有一个类似Hash表的结构存储SessionId和Session
    // HttpSession getSession()：从cookie中获取sessionId并且查询得到session，如果没有查到也能自动新建
    // Cookie/Session 机制很重要的作用就是辅助登陆功能的实现
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 读取请求传来的参数 (用户名和密码)，先给请求设置一下字符集.
        // 否则如果 username 是中文, 此处的 getParameter 可能会乱码.
        req.setCharacterEncoding("utf8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2. 验证用户名和密码，一般来说,验证用户名密码,是要通过数据库的.
        //    此处为了简略, 将用户名和密码初始化为：正确的用户名是 zhangsan, 正确的密码是 123
        //    此处 getParameter 可能会拿到 null.
        //    因此为了避免 空指针异常, 需要使用equals()，其内部能够对参数为null进行处理
        if (!"zhangsan".equals(username) || !"123".equals(password)) {
            // 登录失败
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前的用户名或者密码错误!");
            return;
        }
        // 3. 登录成功, 给这个用户创建一个会话,
        // req.getSession：首先根据Cookie中的sessionId在服务器端查询对应的会话session,
        // 其中的参数“true”表示若session不存在时，自动创建session,并将这个sessionId返回给客户端的Cookie
        HttpSession session = req.getSession(true);
        // 可以给会话中保存一些自定义的数据, 通过 Attribute 的方式让一个数据在多个servlet之间共享
        // 此处 Attribute 也是键值对，同时 Attribute也是会话级别的，每个客户端之间不会互相干扰，
        // 后续跳转到其他的页面, 就随时可以把这个数据从会话中取出来
        session.setAttribute("username", username);
        session.setAttribute("loginTime", System.currentTimeMillis());
        // 4. 此时登录成功， 让页面重定向到网站首页
        resp.sendRedirect("index");
    }
}
