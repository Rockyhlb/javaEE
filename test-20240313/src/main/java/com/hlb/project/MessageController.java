package com.hlb.project;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @BelongsProject: test-20240313
 * @BelongsPackage: com.hlb.project
 * @CreateTime : 2024/3/13 8:43
 * @Description: 留言板
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
    private List<MessageInfo> messageInfos = new LinkedList<>();
    @RequestMapping("/publish")
    public boolean publish(MessageInfo messageInfo) {
        // 判断为空的同时，再排除空格的情况
        if (!StringUtils.hasLength(messageInfo.getFrom().trim())
                || !StringUtils.hasLength(messageInfo.getTo().trim())
                || !StringUtils.hasLength(messageInfo.getInfo().trim())) {
            return false;
        }
        // 排除当输入为空格，但是后端接收到数据的长度并不为0的bug
//        if (messageInfo.getFrom().trim().length() == 0
//                || messageInfo.getTo().trim().length() == 0
//                || messageInfo.getInfo().trim().length() == 0) {
//            return false;
//        }
        // 把数据暂时存放在内存中
        messageInfos.add(messageInfo);
        return true;
    }

    @RequestMapping("/getList")
    public List<MessageInfo> getList() {
        // 直接返回列表
        return messageInfos;
    }
}
