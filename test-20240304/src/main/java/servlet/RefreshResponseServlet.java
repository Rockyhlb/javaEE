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
 * @CreateTime : 2024/3/4 12:57
 * @Description: HttpServletResponse的自动刷新
 * @Author: code_hlb
 */
@WebServlet("/refresh")
public class RefreshResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过设置Header，实现每秒钟自动刷新一次
        resp.setHeader("refresh","1");
        resp.getWriter().write("current time: " + System.currentTimeMillis());
    }
}
