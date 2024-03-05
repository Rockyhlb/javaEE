package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: servlet
 * @CreateTime : 2024/3/5 10:14
 * @Description: 处理来自 博客详情页 展示博客编者信息 的Get请求
 * @Author: code_hlb
 */
@WebServlet("/getAuthorInfo")
public class AuthorInfoServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理博客详情页中显示笔者信息的请求
        // 前端组织的 url: 'getAuthorInfo' + location.search,
        // Get /getAuthorInfo?blogId=1
        // 1、可以直接拿到请求中的query string
        String blogId = req.getParameter("blogId");
        if (blogId == null) {
            // query string 中缺少 blogId
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("请求中缺少 blogId!");
            return;
        }
        // 2、根据blogId查询Blog对象
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.getBlogById(Integer.parseInt(blogId));
        if (blog == null)  {
            // 根据blogId 没有查找到对应blog
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("没有查询到 blogId!");
            return;
        }
        // 3、根据blog中的userId查找用户
        UserDao userDao = new UserDao();
        User user = userDao.getUserById(blog.getUserId());
        if (user == null) {
            // 由于blog表的userId和user表的id建立了外键约束，
            // 所以一般不会出现有blogId查找不到user的情况
            // 这里还是把对应逻辑添加上
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("没有查询到 userId!");
            return;
        }
        // 4、返回user对象
        user.setPassWord("");
        String respJson = objectMapper.writeValueAsString(user);
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write(respJson);
    }
}
