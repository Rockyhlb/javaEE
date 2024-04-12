package com.hlb.blog.mapper;

import com.hlb.blog.model.BlogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.mapper
 * @CreateTime : 2024/4/12 8:37
 * @Description: TODO
 * @Author: code_hlb
 */
@Mapper
public interface BlogMapper {
    // 查询博客列表
    @Select("select * from blog where delete_flag = 0 order by create_time desc")
    List<BlogInfo> queryBlogList();

    // 根据博客ID查询博客详情
    @Select("select * from blog where id = #{blogId} and delete_flag = 0")
    BlogInfo queryBlogById(Integer blogId);

    // 添加博客
    @Insert("insert into blog (title,content,user_id) values (#{title},#{content},#{userId})")
    Integer insertBlog(BlogInfo blog);

    // 根据博客ID修改博客数据
    // 逻辑删除：通过update将delete_flag=1
    // 根据xml编写动态sql
    Integer updateBlog(BlogInfo blogInfo);
}
