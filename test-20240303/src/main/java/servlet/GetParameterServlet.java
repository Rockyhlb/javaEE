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
 * @CreateTime : 2024/3/3 16:15
 * @Description: 获取get请求中的 query string
 * @Author: code_hlb
 */
@WebServlet("/getParameter")
public class GetParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 此处假设query string 是：username=zhangsan&password=0000的形式
        // query sstring 会被Tomcat自动解析成一个Map结构的数据
        // getParameter就是在查询Map<String,String>中的内容
        String userName = req.getParameter("username");
        String passWord = req.getParameter("password");

        System.out.println("userName: " + userName);
        System.out.println("passWord: " + passWord);
        resp.getWriter().write("OK!");
    }
}
