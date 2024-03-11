package com.hlb.firstspringdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.developer.StreamingAttachment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: FirstSpringDemo
 * @BelongsPackage: com.hlb.firstspringdemo
 * @CreateTime : 2024/3/10 20:21
 * @Description: TODO
 * @Author: code_hlb
 */

@RequestMapping("/user/page1")  // @RequestMapping：路由绑定，建立连接，作用在类上表示类路径
// 当一个类有多个注解时，这些注解没有先后顺序
@RestController
public class UserController {
    // MVC：是一种架构涉及的模式，也是一种开发思想，Spring MVC是一种实现
    // Model(模型): 处理业务数据，处理数据
    // View(视图): 界面涉及，人机交互，展示模型数据
    // Controller(控制器)：接收用户请求，选择处理模型，选择视图
    // @RequestMapping 注解作用在方法上表示方法路径，最后客户端访问的url路径为：类路径+方法路径
    // @RequestMapping("/hello1") // 建立连接，默认可以进行Get和Post请求
    @RequestMapping(value = "/hello2",method = RequestMethod.GET) // 指定请求方式为Get
    public String hello() {
        return "hello world";
    }

    // 请求单个参数
    @RequestMapping("/getSingleParam1")
    public String getSingleParam1(String name) {
        return "接收到的参数为：" + name;
    }
    @RequestMapping("/getSingleParam2")
    // 此处的接收类型一般为包装类
    public String getSingleParam2(Integer age) {
        return "接收到的参数为：" + age;
    }

    // 传递多个参数
    @RequestMapping("/getMulParam")
    public String getMulParam(String name,Integer age) {
        // 根据变量名到url中获取对应参数
        return "接收的参数为：" + name + ", " + age;
    }

    // 传递对象
    @RequestMapping("/getObject")
    public String getObject(UserInfo userInfo) {
        return userInfo.toString();
    }

    // @RequestParam(name) String userName: 将前端获取到的参数 name 重命名为 userName
    // @RequestParam 默认为必传参数，当前端没有传入对应参数时会出现400错误
    // required = false, 设置参数为非必传
    @RequestMapping("/renameParam")
    public String renameParam(@RequestParam(value = "name",required = false) String userName,Integer age) {
        return "userName: " + userName + ",age: " + age;
    }

    // 传递数组参数
    @RequestMapping("/getArrays")
    public String getArrays(Integer[] arrays) {
        return Arrays.toString(arrays) + ", length=" + arrays.length;
    }

    // 传递集合参数
    @RequestMapping("/getList")
    public String getList(@RequestParam(value = "list",required = false) List<String> list) {
        // 报错：500
        // 原因：Spring默认在请求中参数名相同的多个值，是封装到数组，或者传入空参数，list出现空指针异常
        // 解决方法：使用 @RequestParam 封装到集合
        if (list != null) {
            return list.toString() + ",length:" + list.size();
        }else {
            return "list为空！";
        }
    }

    // 传递Json数据
    @RequestMapping("/getJson")
    public String getJson(@RequestBody UserInfo userInfo) {
        // 传统方式
        return userInfo.toString();
    }

    // 获取 url 中的参数
    @RequestMapping("/getUrlParam/{name}/{age}")
    public String getUrlParam(@PathVariable(value = "name",required = false) String userName,@PathVariable(value = "age",required = false) Integer age) {
        return "userName: " + userName + ", age: " + age;
    }

    // 传输文件
    @RequestMapping("/getFile")
    public String getFile(@RequestPart MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        file.transferTo(new File("D:/temp/java/" + fileName));
        return "获取到文件：" + fileName;
    }
}
