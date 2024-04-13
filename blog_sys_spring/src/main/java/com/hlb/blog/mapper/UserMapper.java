package com.hlb.blog.mapper;

import com.hlb.blog.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.mapper
 * @CreateTime : 2024/4/12 8:37
 * @Description: TODO
 * @Author: code_hlb
 */
@Mapper
public interface UserMapper {

    // 由于设计表的时候user_name设置的是唯一约束，因此查询结果最多只能有一条数据，可以采用 * 查询
    @Select("select * from user where user_name=#{userName} and delete_flag <> 1")
    UserInfo queryUserByName(String userName);

    @Select("select * from user where id=#{userId} and delete_flag <> 1")
    UserInfo queryUserById(Integer userId);

    @Insert("insert into user (user_name,password,github_url) values (#{userName},#{password},#{githubUrl})")
    Integer register(UserInfo userInfo);

    @Update("update user set password=#{password},github_url=#{githubUrl} where user_name=#{userName}")
    Integer updateUser(UserInfo userInfo);
}
