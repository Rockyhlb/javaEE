package com.hlb.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: test-20240312
 * @BelongsPackage: com.hlb.demo
 * @CreateTime : 2024/3/12 9:39
 * @Description: 返回响应
 * @Author: code_hlb
 */
@RequestMapping("/resp")
/**
 * 可以简单认为，@RestController = @Controller(返回页面) + @ResponseBody(返回数据)
 * @RestController 源码中包含:
 * 元注解：可以注解到 其它注解 的注解
 * @Target({ElementType.TYPE})：表示注解的使用范围
 * @Retention(RetentionPolicy.RUNTIME)：表示注解的生命周期
 * @Documented：表示被其注解的注解将被包含在 Java 文档中
 * @Controller：默认返回页面
 * @ResponseBody：默认返回数据，可以用于类和方法之中，用在类上表示该类所有的方法返回的都是数据
 */
// @RestController
@Controller
public class ResponseController {
    // 返回静态页面，需要类注解是 @Controller
    @RequestMapping("/r1")
    public String r1() {
        // 跳转页面,该路径以 resource目录下的static为根目录
        return "/jump.html";
    }

    @ResponseBody
    @RequestMapping("/r2")
    public String r2() {
        return "hello,world!";
    }

    @ResponseBody
    @RequestMapping("r3")
    public String r3() {
        // Get 可以被缓存，且幂等，Post 不能被缓存
        return "<h1>这是html片段</h1>";
    }

    @ResponseBody
    @RequestMapping("/r4")
    public UserInfo r4() {
        // 返回JSON
        UserInfo user = new UserInfo();
        user.setId(1);
        user.setName("zhangsan");
        user.setAge(22);
        // Spring 会根据返回的响应，动态的设置response 的 content-type
        // Content-Type: application/json
        return user;
    }

    @ResponseBody
    @RequestMapping("/r5")
    public Map<Integer,String> r5() {
        // 返回JSON
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"zhangsan");
        map.put(2,"lisi");
        // Spring 会根据返回的响应，动态的设置response 的 content-type
        // Content-Type: application/json
        return map;
    }

    @RequestMapping("/r6")
    public String r6() {
        // Spring 会根据返回的响应，动态的设置response 的 content-type
        // Content-Type: application/javascript
        return "/a.js";
    }

    @RequestMapping("/r7")
    public String r7() {
        // Spring 会根据返回的响应，动态的设置response 的 content-type
        // Content-Type: text/css
        return "/b.css";
    }

    @ResponseBody
    @RequestMapping("/r8")
    public String r8(HttpServletResponse response) {
        response.setStatus(401);
        // 状态码的设置不影响页面的展示
        return "设置状态码成功！";
    }

    // 设置Header
    @ResponseBody
    /**
     * @RequestMapping 中的可设置参数：
     * value: 指定映射的URL
     * method: 指定请求的method类型 Get/Post
     * consumes: 指定请求提交的内容类型(Content-Type)
     * produces: 指定返回的内容类型，只有请求头中的(Accept)类型中包含该指定类型才返回
     * params: 指定request中必须包含指定参数值，才让该方法处理
     * headers: 指定request中必须包含某些指定的header值，才让该方法处理请求
     */
    @RequestMapping(value = "/r9",produces = "application/Json; charset=utf8")
    // @RequestMapping("/r9")
    public String r9() {
        // return "1234";
        return "{200:\"Ok\"}";
    }

    @ResponseBody
    @RequestMapping("/r10")
    public String r10(HttpServletResponse response) {
        response.setHeader("MyHeader","10");
        return "设置Header成功！";
    }
}
