package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: test-20240303
 * @BelongsPackage: servlet
 * @CreateTime : 2024/3/3 16:43
 * @Description: 通过表单数据绑定body,发送post请求
 * @Author: code_hlb
 */
@WebServlet("/postParameter")
public class PostParameterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // body使用form表单传递参数
        // 假设，前端构造这样的请求:
        // POST /postParameter
        // Content-Type: x-www-form-urlencoded
        // username=zhangsan&password=0000

        // 就需要在后端代码中, 把 body 中的 值 给拿到
        // 获取值的方法, 仍然是 getParameter
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username=" + username);
        System.out.println("password=" + password);
        resp.getWriter().write("ok");
    }
}
