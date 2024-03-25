package com.hlb.project.module;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: test-20240313
 * @BelongsPackage: com.hlb.project
 * @CreateTime : 2024/3/25 14:32
 * @Description: 对应 message_info 表的实体
 * @Author: code_hlb
 */
@Data
public class MessageInfo {
    private Integer id;
    private String from;
    private String to;
    private String message;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
