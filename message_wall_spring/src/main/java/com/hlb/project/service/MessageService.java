package com.hlb.project.service;

import com.hlb.project.mapper.MessageMapper;
import com.hlb.project.module.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: message_wall_spring
 * @BelongsPackage: com.hlb.project.service
 * @CreateTime : 2024/3/25 14:59
 * @Description: TODO
 * @Author: code_hlb
 */
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public boolean addMessage(MessageInfo messageInfo) {
        return 0 != messageMapper.insertMessage(messageInfo);
    }

    public List<MessageInfo> queryMessageList() {
        return messageMapper.queryMessageList();
    }
}
