package com.hlb.conf.controller;

import com.hlb.conf.model.Dbtypes;
import com.hlb.conf.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.controller
 * @CreateTime : 2024/3/18 14:27
 * @Description: TODO
 * @Author: code_hlb
 */
@RestController
@RequestMapping("/yml")
public class YamlController {
    @Value("${string.value}")
    private String value1;
    @Value("${string.str1}")
    private String str1;
    @Value("${string.str2}")
    private String str2;
    @Value("${string.str3}")
    private String str3;
    @RequestMapping("/readValue")
    public String readValue() {
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        return "获取到的value: " + value1;
    }

    @Value("${null.value}")
    private String nullVal;
    @RequestMapping("/readNull")
    public String readNull() {
        // “len: 0,val: ”
        return "len: " + nullVal.length() + ",val: " + nullVal;
    }

    @Value("${empty.value}")
    private String emptyStr;
    @RequestMapping("/readEmptyStr")
    public String readEmptyStr() {
        // “len: 0,val: ”
        return "len: " + emptyStr.length() + ",val: " + emptyStr;
    }

    @Autowired
    private Student student;
    @RequestMapping("/readStu")
    public String readStu() {
        // 读取对象
        return student.toString();
    }

    @Autowired
    private Dbtypes dbtypes;
    @RequestMapping("readTypes")
    public String readTypes() {
        // 获取集合
        System.out.println("nameLen:" + dbtypes.getName().size() + ", name: " + dbtypes.getName().toString());
        // 获取 Map
        System.out.println("mapLen:" + dbtypes.getMap().size() + ", Map=" + dbtypes.getMap().toString());
        // 获取对象
        return dbtypes.toString();
    }
}
