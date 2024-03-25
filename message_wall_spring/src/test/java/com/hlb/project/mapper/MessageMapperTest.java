package com.hlb.project.mapper;

import com.hlb.project.module.MessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MessageMapperTest {
    @Autowired
    private MessageMapper messageMapper;
    @BeforeEach
    void setUp() {
        log.info("Starting..............");
    }

    @AfterEach
    void tearDown() {
        log.info("Ending..............");
    }

    @Test
    void insertMessage() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setFrom("小狗");
        messageInfo.setTo("小猫");
        messageInfo.setMessage("汪汪汪");
        log.info(messageMapper.insertMessage(messageInfo).toString());
    }

    @Test
    void queryMessageList() {
        log.info(messageMapper.queryMessageList().toString());
    }
}