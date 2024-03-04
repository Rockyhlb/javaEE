package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: test-20240304
 * @BelongsPackage: servlet
 * @CreateTime : 2024/3/4 13:01
 * @Description: HttpServletResponse的重定向
 * @Author: code_hlb
 */
@WebServlet("/redirect")
public class RedirectResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 重定向的状态码是 3XX,例如302
        resp.setStatus(302);
        // header 需要有一个 Location属性，描述要跳转到哪
        resp.setHeader("Location","https://www.baidu.com");
    }
}
