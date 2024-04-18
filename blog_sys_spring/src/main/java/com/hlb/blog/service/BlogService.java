package com.hlb.blog.service;

import com.hlb.blog.mapper.BlogMapper;
import com.hlb.blog.model.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.service
 * @CreateTime : 2024/4/12 10:40
 * @Description: BlogService
 * @Author: code_hlb
 */
@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public List<BlogInfo> getBlogList() {
        // 统一异常处理中已经对异常进行处理
        return blogMapper.queryBlogList();
    }

    public BlogInfo queryBlogById(Integer blogId) {
        return blogMapper.queryBlogById(blogId);
    }

    public Integer publishBlog(BlogInfo blogInfo) {
        return blogMapper.insertBlog(blogInfo);
    }

    public Integer updateBlog(BlogInfo blogInfo) {
        return blogMapper.updateBlog(blogInfo);
    }

    public Integer deleteBlog(BlogInfo blogInfo) {
        return blogMapper.updateBlog(blogInfo);
    }

    public Integer selectSumArticles(Integer userId) {
        return blogMapper.selectSumArticles(userId);
    }
}
