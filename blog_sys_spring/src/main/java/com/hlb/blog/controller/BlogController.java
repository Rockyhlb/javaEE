package com.hlb.blog.controller;

import com.hlb.blog.constants.Constant;
import com.hlb.blog.model.BlogInfo;
import com.hlb.blog.service.BlogService;
import com.hlb.blog.utils.JWTUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.controller
 * @CreateTime : 2024/4/12 10:39
 * @Description: BlogController
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfo> getBlogList() {
        List<BlogInfo> modifyList = new LinkedList<>();
        for (BlogInfo blog : blogService.getBlogList()) {
            // 当正文内容大于100字符时，将其剪切后再发回给前端
            if (blog.getContent().length() > 100) {
                blog.setContent(blog.getContent().substring(0, 100) + "...");
            }
            modifyList.add(blog);
        }
        return modifyList;
    }

    @RequestMapping("getBlogDetail")
    public BlogInfo getBlogDetail(Integer blogId) {
        return blogService.queryBlogById(blogId);
    }

    @RequestMapping("publishBlog")
    public Boolean publishBlog(String title, String content, HttpServletRequest request) {
        if (content.length() > 50) {
            log.info("publishBlog,title:{},content:{}",title,content.substring(0,10));
        }else {
            log.info("publishBlog,title:{},content:{}",title,content);
        }

        // 1、参数校验
        if (!StringUtils.hasLength(title.trim()) || !StringUtils.hasLength(content.trim())) {
            log.error("title 或 content 不能为空~");
            return false;
        }
        // 2、获取用户ID
        String token = request.getHeader(Constant.USER_TOKEN_HEADER);
        Integer userId = JWTUtils.getUserIdFromToken(token);
        if (userId == null || userId <= 0) {
            log.error("用户未登陆!");
            return false;
        }
        // 3、发布博客
        BlogInfo blogInfo = new BlogInfo(title,content,userId);
        Integer res = blogService.publishBlog(blogInfo);
        if (res <= 0) {
            log.error("博客发布失败！");
            return false;
        }
        return true;
    }

    @RequestMapping("updateBlog")
    public Boolean updateBlog(Integer blogId,String title,String content) {
        if (content.length() > 50) {
            log.info("updateBlog,blogId:{},title:{},content:{}",blogId,title,content.substring(0,20));
        }else {
            log.info("updateBlog,blogId:{},title:{},content:{}",blogId,title,content);
        }
        // 1、校验参数
        if (blogId == null || !StringUtils.hasLength(title.trim()) || !StringUtils.hasLength(content)) {
            log.error("ID/标题/正文 不能为空！");
            return false;
        }
        // 2、封装对象
        BlogInfo blogInfo = new BlogInfo(blogId,title,content);
        Integer res = blogService.updateBlog(blogInfo);
        if (res <= 0) {
            log.error("更新失败");
            return false;
        }
        return true;
    }

    @RequestMapping("deleteBlog")
    public Boolean deleteBlog(Integer blogId) {
        log.info("deleteBlog, blogId: " + blogId);
        if (blogId == null || blogId <= 0) {
            log.error("blogId 非法！");
            return false;
        }
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(1);
        Integer res = blogService.deleteBlog(blogInfo);
        if (res <= 0) {
            log.error("更新失败");
            return false;
        }
        return true;
    }
}