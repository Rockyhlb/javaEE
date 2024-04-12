package com.hlb.blog.controller;

import com.hlb.blog.model.BlogInfo;
import com.hlb.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}