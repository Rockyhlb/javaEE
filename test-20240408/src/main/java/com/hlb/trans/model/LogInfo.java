package com.hlb.trans.model;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.model
 * @CreateTime : 2024/4/8 13:33
 * @Description: 日志表实体类
 * @Author: code_hlb
 */
@Data
public class LogInfo {
    private Integer id;
    private String userName;
    private String op;
    private Date createTime;
    private Date updateTime;
}
