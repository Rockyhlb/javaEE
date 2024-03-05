package servlet;

import model.User;
import model.UserDao;

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
 * @CreateTime : 2024/3/5 10:08
 * @Description: 处理来自 登陆页面 的Post请求 以及 各个页面判断当前登陆状态的 Get请求
 * @Author: code_hlb
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理登陆请求
        // 1、获取前端发送过来的账号和密码
        req.setCharacterEncoding("utf8");
        String userName = req.getParameter("userName");
        String userPassWord = req.getParameter("passWord");
        // 当输入框内容不合法时
        if (userName == null || userName.length() == 0 || userPassWord == null || userPassWord.length() == 0) {
            resp.setContentType("text/html; charset = utf8");
            // 打印日志进行排错
            // System.out.println("{"+ userName + ": " + userPassWord + "}");
            // 采用模糊返回错误的方式，防止被人恶意猜中用户名
            resp.getWriter().write("您输入的用户名或密码不正确...");
        }
        // 2、查询数据库，查看此处的密码是否正确
        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(userName);
        if (user == null) {
            // 未查询到用户
            resp.setContentType("text/html; charset=utf8");
            // System.out.println("{"+ userName + ": " + userPassWord + "}");
            resp.getWriter().write("您的用户名或密码不正确...");
            return;
        }
        if (!userPassWord.equals(user.getPassWord())) {
            // 密码错误
            resp.setContentType("text/html; charset=utf8");
            // System.out.println("{"+ userName + ": " + userPassWord + "}");
            resp.getWriter().write("您的用户名或密码不正确...");
            return;
        }
        // 3、验证成功，创建会话，允许新建
        HttpSession session = req.getSession(true);
        // 在会话中存储用户信息
        session.setAttribute("user",user);
        // 4、跳转到主页
        resp.sendRedirect("blog_list.html");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理强制登陆需求，此处约定登陆成功的状态码为：200，登陆失败的状态码为：403
        // 通过判断会话是否存在，并且会话中要能保存 user 对象.
        // (这样就可以后续通过删除会话中的user来实现退出登陆的效果)
        HttpSession session = req.getSession(false);
        if (session == null) {
            // 会话不存在, 用户属于未登录状态.
            resp.setStatus(403);
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // user 对象也不存在，同样也属于未登录状态
            resp.setStatus(403);
            return;
        }
        // 两个都存在, 返回 200
        // 此处 200 不写也行，默认就是 200
        resp.setStatus(200);
    }
}
