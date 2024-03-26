package com.hlb.mapper;

import com.hlb.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.mapper
 * @CreateTime : 2024/3/25 16:24
 * @Description: mapper: user
 * @Author: code_hlb
 */
@Mapper
public interface UserMapper {
    @Select("select * from user_info where user_name=#{userName} and delete_flag = 0")
    UserInfo queryByUserName(String userName);
}
