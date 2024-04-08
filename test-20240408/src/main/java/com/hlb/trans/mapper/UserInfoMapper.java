package com.hlb.trans.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.mapper
 * @CreateTime : 2024/4/8 13:39
 * @Description: TODO
 * @Author: code_hlb
 */
@Mapper
public interface UserInfoMapper {
    @Insert("insert into user_info (user_name,password) values (#{userName},#{password})")
    Integer insertUser(String userName,String password);
}
