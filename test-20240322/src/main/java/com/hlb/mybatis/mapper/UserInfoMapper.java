package com.hlb.mybatis.mapper;

import com.hlb.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.mapper
 * @CreateTime : 2024/3/22 11:15
 * @Description: 数据访问层：放持久层代码，写在 mapper 或者 dao下
 * @Author: code_hlb
 */
@Mapper
/*
* @Mapper注解：表⽰是MyBatis中的Mapper接⼝
• 程序运⾏时, 框架会⾃动⽣成接⼝的实现类对象(代理对象)，并交给Spring的IOC容器管理
* */
public interface UserInfoMapper {
    @Select("select * from user_info") // 方法的实现
    List<UserInfo> queryAllUser();     // 方法的声明
    // Mybatis 不支持方法重载，因此必须一个方法一个名
    // List<UserInfo> queryAllUser();

    // 参数绑定：
    // 1、默认是方法的参数名称,当只有一个参数时，不影响参数的传递，多个参数必须保持参数名一致
    // 2、如果使用注解 @Param 对参数进行重命名，那么就必须使用重命名后的参数
    @Select("select * from user_info where id = #{id}")
    UserInfo queryUserById(Integer userId);
    @Select("select * from user_info where id = #{userId} and delete_flag = #{deleteFlag}")
    UserInfo queryUserByParam(Integer userId,Integer deleteFlag);
    @Select("select * from user_info where id = #{id} and delete_flag = #{deleteFlag}")
    UserInfo queryUserByParam1(@Param("id") Integer userId,Integer deleteFlag);

    // --> 增
    // user_info() 括号里是表字段， values() 括号里是java类属性
    // 使用对象来传递参数：
    // 1、如果没有使用 @Param 进行重命名，直接使用属性名传递参数就行
    // 2、如果使用 @Param 进行重命名，那么就只能使用 对象名.属性 来传递参数
    @Insert("insert into user_info(username,password,age,gender,phone) " +
            "values (#{username},#{password},#{age},#{gender},#{phone})")
    Integer insert(UserInfo userInfo);
    // 通过 @Options注解 返回主键
    // useGeneratedKeys：让MyBatis 使⽤ JDBC的 getGeneratedKeys() 取出由数据库内部⽣成的主键
    // keyProperty：指定能够唯一识别对象的属性
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user_info(username,password,age,gender,phone) " +
            "values (#{user.username},#{user.password},#{user.age},#{user.gender},#{user.phone})")
    Integer insertByParam(@Param("user") UserInfo userInfo);

    // --> 删
    @Delete("delete from user_info where id = #{userId}")
    Integer deleteByUserId(@Param("userId") Integer id);

    // --> 改
    @Update("update user_info set username=#{user.username},password=#{user.password}," +
            "update_time=#{user.updateTime} where id=#{user.id}")
    Integer updateByUserId(@Param("user") UserInfo userInfo);

}
