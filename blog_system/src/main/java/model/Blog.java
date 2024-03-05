package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: model
 * @CreateTime : 2024/3/5 9:19
 * @Description: Blog对应数据库中blog表的一条记录
 * @Author: code_hlb
 */
public class Blog {
    private int blogId;
    private String title;
    private String content;
    // Timestamp允许 JDBC API 将该类标识为 SQL TIMESTAMP 值。
    private Timestamp postTime;
    private int userId;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostTime() {
        // 默认返回的是时间戳格式
        // return postTime;
        // 进行格式化时间的转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(postTime);
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                ", userId=" + userId +
                '}';
    }
}
