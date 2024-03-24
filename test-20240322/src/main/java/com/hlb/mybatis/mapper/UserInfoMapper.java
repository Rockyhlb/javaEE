package com.hlb.mybatis.mapper;

import com.hlb.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: test-20240322
 * @BelongsPackage: com.hlb.mybatis.mapper
 * @CreateTime : 2024/3/22 11:15
 * @Description: 数据访问层：放持久层代码，写在 mapper 或者 dao下，该类由注解实现
 * @Author: code_hlb
 */
@Mapper
/*
* @Mapper注解：表⽰是MyBatis中的Mapper接⼝
• 程序运⾏时, 框架会⾃动⽣成接⼝的实现类对象(代理对象)，并交给Spring的IOC容器管理
* */
public interface UserInfoMapper {
    // --> 查, 此处为了方便都是全字段查询(select * from XXX), 平时应该尽量避免
    @Select("select * from user_info") // 方法的实现
    List<UserInfo> queryAllUser();     // 方法的声明
    // Mybatis 不支持方法重载，因此必须一个方法一个名
    @Select("select id,username,password,age,gender,phone," +
            "delete_flag,create_time,update_time from user_info")
    List<UserInfo> queryAllUser1();
    // 当java属性和sql的字段一致时，mybatis会进行自动赋值，如果不一样，则不赋值，导致createTime=null
    // 解决方法：
    // 1、改别名
    @Select("select id,username,password,age,gender,phone," +
            "delete_flag as deleteFlag,create_time as createTime,update_time as updateTime" +
            " from user_info")
    List<UserInfo> queryAllUser2();
    // 2、通过注解来告诉Mybatis: java属性 和 sql字段的映射关系
    // 如果其他Sql, 也需要复⽤这个映射关系, 可以给这个Results定义一个名称
    @Results(id = "BaseResult",value = {
            // column: 填写sql字段名
            // property: 填写java映射属性
            @Result(column = "delete_flag",property = "deleteFlag"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    @Select("select * from user_info")
    List<UserInfo> queryAllUser3();
    // 通过 @ResultMap 复用之前定义的 BaseResult
    @ResultMap(value = "BaseResult")
    @Select("select * from user_info")
    List<UserInfo> queryAllUser4();
    // 3、在配置文件中 配置自动驼峰转换

    @ResultMap(value = "BaseResult")
    @Select("select * from user_info where id = #{id}")
    UserInfo queryUserById(Integer userId);
    // 参数绑定：
    // 1、默认是方法的参数名称,当只有一个参数时，不影响参数的传递，多个参数必须保持参数名一致
    // 2、如果使用注解 @Param 对参数进行重命名，那么就必须使用重命名后的参数
    @ResultMap(value = "BaseResult")
    @Select("select * from user_info where id = #{userId} and delete_flag = #{deleteFlag}")
    UserInfo queryUserByParam(Integer userId,Integer deleteFlag);
    @ResultMap(value = "BaseResult")
    @Select("select * from user_info where id = #{id} and delete_flag = #{deleteFlag}")
    UserInfo queryUserByParam1(@Param("id") Integer userId,Integer deleteFlag);

    // #{} 使⽤的是预编译SQL (Preparing: select * from user_info where id=?), 通过 ? 占位的⽅式, 提前对SQL进⾏编译,
    // 然后把参数填充到SQL语句中. #{} 会根据参数类型, 默认拼接引号 ''
    // ${} 会直接进行字符替换 (Preparing: select * from user_info where id=3),
    // 然后对SQL进⾏编译. 因此如果参数为字符串, 需要加上引号 ''
    @ResultMap(value = "BaseResult")
    @Select("select * from user_info where id=${id}")
    UserInfo queryUserByParam2(Integer id);
    @ResultMap(value = "BaseResult")
    @Select("select * from user_info where username='${name}'") // 不加引号：java.sql.SQLSyntaxErrorException: Unknown column 'zhangsan' in 'where clause'
    List<UserInfo> queryUserByStr(String name);
    /*
    * #{} 和 ${} 的区别就是预编译SQL和即时SQL 的区别
    * 预编译Sql 性能更高(通过占位符'?'进行赋值)，且更安全(防止Sql注入)，即时Sql就会发生Sql注入的风险
    * SQL注⼊：通过操作输⼊的数据来修改事先定义好的SQL语句，以达到执⾏代码对服务器进⾏攻击的⽅法
    * sql 注⼊代码： username = ' or 1='1
    * 如果实现定义好的Sql语句是：select * from user_info where username=${username}
    * 代入后的Sql语句就变成了：select * from user_info where username='' or 1='1'，也就可以获取到表中的所有数据了
    * */
    @ResultMap(value = "BaseResult")
    @Select("select * from user_info where username='${username}'")
    List<UserInfo> queryUserByUserName(String username);

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
    @Update("update user_info set password=#{password},update_time=#{updateTime} where id=#{id}")
    Integer updateByUserId(String password, Date updateTime, Integer id);

    @Update("update user_info set username=#{user.username},password=#{user.password}," +
            "update_time=#{user.updateTime} where id=#{user.id}")
    Integer updateByUserOb(@Param("user") UserInfo userInfo);

    // 模拟Sql注入，完成登陆验证
    @Select("select * from user_info where username='${userName}' and password='${passWord}'")
    List<UserInfo> queryUserByNameAndPassword(String userName, String passWord);

    // ${}存在的必要性：例如当排序规则desc/asc,这些固定属性都不能加上引号，因此就需要使用上${}
    // 当不需要加引号的时候(比如表名，字段名，一些固定属性)，#{}都不能使用
    @Select("select * from user_info order by id ${order}")
    // 但是${}存在Sql注入的危险，我们可以通过约定一些规则或者对参数进行校验的方式来避免
    List<UserInfo> queryUserOrder(String order);

    // #{} 无法实现like模糊查询
//    @Select("select * from user_info where username like '%#{name}%'")
    // 把 #{} 改成 ${} 可以正确查出来, 但是${}存在SQL注⼊的问题, 所以不能直接使⽤ ${}.
    // 解决办法: 使⽤ mysql 的内置函数 concat() 来处理，
    @Select("select * from user_info where username like CONCAT('%',#{name},'%')")
    List<UserInfo> queryUserByLike(String name);

    // 也可以通过注解的方式完成动态Sql，但是可读性太差，因此不推荐
    @Delete("<script>" +
            "delete from userinfo where id in "+
            "<foreach collection='list' separator=',' open='(' close=')' item='id'> "+
            "#{id} "+
            "</foreach> "+
            "</script> ")
    Integer batchDelete(List<Integer> ids);
}
