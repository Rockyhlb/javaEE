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
 * @CreateTime : 2024/3/3 13:10
 * @Description: 在客户端返回 ”hello world“
 * @Author: code_hlb
 */
@WebServlet("/hello") // “配置路由”：让这个类和一个Http请求路径关联在一起
public class HelloServlet extends HttpServlet {
    // Servlet:是Tomcat(Http服务器)封装的一组API,封装了Http协议
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 此处的打印是显示tomcat的控制台上，也就是服务器端
        System.out.println("hello world");
        // 要想把内容显示到页面上，就需要把字符串写入到 http响应 的body中
        // 由于idea全局编码格式是utf-8,而浏览器默认的解析方式是gbk，因此浏览器中会出现乱码
        // 解决方法：在http响应保文中，显示告诉浏览器返回的body字符集的格式
        resp.setContentType("text/html; charset=utf8");
        resp.getWriter().write("你好 world!");
    }
    // 404：用户访问的资源不存在，一般是由于URL(Context Path + Server Path)的路径不正确导致的
    // 405：方法没有被实现(GET请求，但是Servlet中没有重写doGet,或者就是重写的代码中没有删掉super)
    // 500：服务器内部错误，由于代码出现异常而导致的，在日志或响应中会带有异常信息
    // 出现空白页面：可能是由于服务器没有成功返回带有正文的响应报文引起的
    // 无法访问此网站：Tomcat没有正确运行
}
