package com.hlb.trans.mapper;

import com.hlb.trans.model.LogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.mapper
 * @CreateTime : 2024/4/9 9:28
 * @Description: LogInfoMapper
 * @Author: code_hlb
 */
@Mapper
public interface LogInfoMapper {
    @Insert("insert into log_info (user_name,op) values (#{userName},#{op})")
    Integer insert(LogInfo logInfo);
}
