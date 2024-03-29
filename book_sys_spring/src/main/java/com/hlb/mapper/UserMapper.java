package com.hlb.mapper;

import com.hlb.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Insert("insert into user_info(`user_name`,`password`,`email`) values (#{userName},#{password},#{email})")
    Integer register(UserInfo userInfo);

    @Update("update user_info set password=#{userInfo.password},email=#{userInfo.email} where id=#{oldUserId}")
    Integer updateUser(UserInfo userInfo, Integer oldUserId);
}
