package com.hlb.project.controller;

import com.hlb.project.module.MessageInfo;
import com.hlb.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: test-20240313
 * @BelongsPackage: com.hlb.project
 * @CreateTime : 2024/3/125 15:00
 * @Description: 表现层: 留言板
 * @Author: code_hlb
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    // 约定前后端交互接口
    // 发布留言：                       获取
    // type: post                     Get
    // URL: /message/publish          /message/getList
    // 参数：from,to,info              无
    // 响应：true/false                List<MessageInfo>
    @Autowired
    private MessageService messageService;

    @RequestMapping("/publish")
    public boolean publish(MessageInfo messageInfo) {
        // 判断输入为空的同时，再排除空格的情况
        if (!StringUtils.hasLength(messageInfo.getFrom().trim())
                || !StringUtils.hasLength(messageInfo.getTo().trim())
                || !StringUtils.hasLength(messageInfo.getMessage().trim())) {
            return false;
        }
        return messageService.addMessage(messageInfo);
    }

    @RequestMapping("/getList")
    public List<MessageInfo> getList() {
        // 直接调用业务逻辑层 service 返回数据
        return messageService.queryMessageList();
    }
}
