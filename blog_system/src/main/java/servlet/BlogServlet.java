package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: servlet
 * @CreateTime : 2024/3/5 10:12
 * @Description: 处理来自 博客列表页和博客详情页 展示博客列表和博客详情的Get请求，以及来自 博客编辑页的发布博客 Post请求
 * @Author: code_hlb
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogDao blogDao = new BlogDao();
        // 由于博客详情页和博客列表页都会发送get请求，但是一个带query string,另一个不带，
        // 因此可以通过query string区分是哪种请求
        String blogId = req.getParameter("blogId");
        String respJsonBlogs = "";
        if (blogId == null) {
            // 请求来自博客列表页
            // 查询数据库，得到博客列表
            List<Blog> blogList = blogDao.getBlogs();
            // 把博客列表数据组织成json数据返回
            respJsonBlogs = objectMapper.writeValueAsString(blogList);
        }else {
            // 请求来自博客详情页
            Blog blog = blogDao.getBlogById(Integer.parseInt(blogId));
            respJsonBlogs = objectMapper.writeValueAsString(blog);
        }
        System.out.println("respJsonBlogs: " + respJsonBlogs);
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write(respJsonBlogs);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理发布博客时的post请求
        // 约定请求中的数据格式为form,body中的格式为：title=“这是标题”&content="这是内容"
        // 1、读取请求中的参数
        req.setCharacterEncoding("utf8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || title.length() == 0 || content == null || content.length() == 0) {
            // 不满足发布博客的条件
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("缺少标题或正文内容，无法发布博客！");
            return;
        }
        // 2、从当前会话中取出user
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前用户未登录");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前用户未登录");
            return;
        }
        // 3、构造博客
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        // 设置当前用户的id
        blog.setUserId(user.getId());
        // 4、保存到数据库中
        BlogDao blogDao = new BlogDao();
        try {
            blogDao.addBlog(blog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 5、302：重定向到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}
