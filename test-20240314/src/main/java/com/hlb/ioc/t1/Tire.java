package com.hlb.ioc.t1;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.t1
 * @CreateTime : 2024/3/14 10:48
 * @Description: 引入IOC造车
 * @Author: code_hlb
 */
public class Tire {
//    private int DEFAULT_SIZE = 17;
    private int size;
    public Tire(int size) {
        System.out.println("tire's size: " + size);
    }
}
