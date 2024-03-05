package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: model
 * @CreateTime : 2024/3/5 9:32
 * @Description: 提供访问数据库数据的接口
 * @Author: code_hlb
 */
public class BlogDao {
    // 1、发布博客
    public void addBlog(Blog blog) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 建立连接
            connection = DBUtil.getConnection();
            // 构造sql
            String sql = "insert into blog values (null,?,?,now(),?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,blog.getTitle());
            preparedStatement.setString(2,blog.getContent());
            preparedStatement.setInt(3,blog.getUserId());
            // 执行sql
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBUtil.close(connection,preparedStatement,null);
        }
    }
    // 2、查询博客列表
    public List<Blog> getBlogs() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Blog> blogList = new LinkedList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog order by postTime desc";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            // 写入List
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                // 此处由于正文内容可能会过多，因此为了美观需要进行截取字符串
                String subContent = resultSet.getString("content");
                if (subContent.length() > 200) {
                    subContent = subContent.substring(0,200) + "...";
                }
                blog.setContent(subContent);
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                blogList.add(blog);
            }
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return blogList;
    }
    // 3、根据Id查询对应博客内容
    public Blog getBlogById(int blogId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog where blogId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,blogId);

            resultSet = preparedStatement.executeQuery();
            // 写入List
            if (resultSet.next()) {
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                // 此处是列表详情页，因此不需要进行截断
                blog.setContent(resultSet.getString("content"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                return blog;
            }
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return null;
    }
    // 4、根据博客Id，删除博客
    public void deleteBlogById(int blogId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
}
