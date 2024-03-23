package com.hlb.mybatis.mapper;

import com.hlb.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.mapper
 * @CreateTime : 2024/3/23 22:15
 * @Description: xml实现
 * @Author: code_hlb
 */
@Mapper
public interface UserInfoXmlMapper {
    // Mybatis 的开发主要分为两种，分别是注解 和 xml
    // xml 的实现主要为：
    // 1、引入Mybatis依赖，配置数据库连接
    // 2、配置 mapper 的路径
    // 3、声明 Mapper接口 和 xml实现
    List<UserInfo> queryAllUser();
    List<UserInfo> queryAllUser1();
    // 参数绑定：
    UserInfo queryUserByParam(@Param("id") Integer userId,Integer deleteFlag);

    // --> 增
    Integer insertByOb(@Param("user") UserInfo userInfo);

    // --> 删
    Integer deleteByUserId(@Param("userId") Integer id);

    // --> 改
    Integer updateByUserId(String password, Date updateTime, Integer id);
    Integer updateByUserOb(@Param("user") UserInfo userInfo);

}
