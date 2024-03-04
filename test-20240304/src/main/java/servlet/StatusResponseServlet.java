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
 * @CreateTime : 2024/3/4 12:48
 * @Description: HttpServletResponse的状态码设置
 * @Author: code_hlb
 */
@WebServlet("/status")
public class StatusResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 不显示设置的情况下，默认是200
        // resp.setStatus(404);
        resp.sendError(404,"该页面不存在");
    }
}
