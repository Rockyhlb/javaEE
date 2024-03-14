package com.hlb.ioc.t2;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.t1
 * @CreateTime : 2024/3/14 10:48
 * @Description: TODO
 * @Author: code_hlb
 */
public class Tire {
    private int size;
    public Tire(int size,String color) {
        System.out.println("tire's size: " + size + ", color: " + color);
    }
}
