package com.hlb.adapt;

/**
 * @BelongsProject: test-20240330
 * @BelongsPackage: com.hlb.adapt
 * @CreateTime : 2024/3/30 22:24
 * @Description: 测试 Slf4jApi
 * @Author: code_hlb
 */
public class Slf4jTest {
    public static void main(String[] args) {
        // 通过调用相同的方法就可以完成不同底层的实现
        Sl4jApi sl4jApi = new Slf4jLog4jAdapter(new Log4j());
        sl4jApi.log("hello world");

        Sl4jApi sl4jApi1 = new Slf4jLogbackAdapter(new LogBack());
        sl4jApi1.log("hello world");
    }
}
