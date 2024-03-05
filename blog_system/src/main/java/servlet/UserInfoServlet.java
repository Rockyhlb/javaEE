package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

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
 * @CreateTime : 2024/3/5 10:13
 * @Description: 处理来自 博客详情页 展示当前登陆用户信息 的Get请求
 * @Author: code_hlb
 */
@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 由于该请求是在列表页显示用户信息，因此只需取出当前会话，然后返回信息即可
        HttpSession session = req.getSession(false);
        if (session == null) {
            // 会话不存在
            resp.setContentType("text/html; charset = utf8");
            resp.getWriter().write("当前用户未登陆！");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 会话存在，但是会话当中的信息已经被删除
            resp.setContentType("text/html; charset = utf8");
            resp.getWriter().write("当前用户未登陆！");
            return;
        }
        resp.setContentType("application/json; charset=utf8");
        // 由于user当中还有 passWord 属性，因此不适合在响应中返回
        user.setPassWord("");
        String respJson = objectMapper.writeValueAsString(user);
        // System.out.println("{ " + respJson + " }");
        resp.getWriter().write(respJson);
    }
}
