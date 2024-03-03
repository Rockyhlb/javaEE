package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @BelongsProject: test-20240303
 * @BelongsPackage: servlet
 * @CreateTime : 2024/3/3 15:48
 * @Description: 获取请求中的属性
 * @Author: code_hlb
 */
@WebServlet("/showRequest")
public class ShowRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用API，把得到的结果构造成一个字符串后返回给客户端
        StringBuilder stringBuilder = new StringBuilder();
        // 获取请求协议的名称和版本
        stringBuilder.append(req.getProtocol());
        stringBuilder.append("<br>");
        // 获取请求的Http的方法名称
        stringBuilder.append(req.getMethod());
        stringBuilder.append("<br>");
        // URI：唯一资源标识符，是URL的一部分
        stringBuilder.append(req.getRequestURI());
        stringBuilder.append("<br>");
        // 返回上下文的请求URI部分
        stringBuilder.append(req.getContextPath());
        stringBuilder.append("<br>");
        // 返回查询字符串
        stringBuilder.append(req.getQueryString());
        stringBuilder.append("<br>");
        // getHeaderNames返回一个枚举，包含所有的头名
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = req.getHeader(key);
            stringBuilder.append(key + ": " + value + "<br>");
        }
        // 把响应返回给客户端
        resp.setContentType("text/html; charset=utf8");
        resp.getWriter().write(stringBuilder.toString());
    }
}
