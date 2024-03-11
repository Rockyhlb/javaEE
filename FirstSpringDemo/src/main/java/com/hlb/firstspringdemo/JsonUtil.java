package com.hlb.firstspringdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jws.soap.SOAPBinding;

/**
 * @BelongsProject: FirstSpringDemo
 * @BelongsPackage: com.hlb.firstspringdemo
 * @CreateTime : 2024/3/11 14:32
 * @Description: TODO
 * @Author: code_hlb
 */
public class JsonUtil {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = new UserInfo(1,"zhangsan",20);
        // 对象 --> json
        String str = objectMapper.writeValueAsString(userInfo);
        System.out.println("json: " + str);

        // json --> 对象
        UserInfo userInfo1 = objectMapper.readValue(str,UserInfo.class);
        System.out.println(userInfo1.toString());
    }
}
