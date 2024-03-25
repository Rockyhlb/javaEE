package com.hlb.project.mapper;

import com.hlb.project.module.MessageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: message_wall_spring
 * @BelongsPackage: com.hlb.project.mapper
 * @CreateTime : 2024/3/25 14:35
 * @Description: TODO
 * @Author: code_hlb
 */
@Mapper
public interface MessageMapper {
    // 插入留言 --> 此处插入Sql语句简单，采用注解的方式
    // 由于 'from' 'to' 都是特殊字段,因此需要加入 `` 包裹
    @Insert("insert into message_info (`from`,`to`,`message`) values (#{from},#{to},#{message}) ")
    Integer insertMessage(MessageInfo messageInfo);

    @Select("select * from message_info where delete_flag <> 1")
    List<MessageInfo> queryMessageList();
}
