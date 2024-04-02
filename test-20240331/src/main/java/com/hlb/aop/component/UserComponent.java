package com.hlb.aop.component;

import org.springframework.stereotype.Component;

/**
 * @BelongsProject: test-20240331
 * @BelongsPackage: com.hlb.aop.component
 * @CreateTime : 2024/4/2 19:10
 * @Description: 实现接口类
 * @Author: code_hlb
 */
@Component
public class UserComponent implements IFace {
//    @Override
    public void sayHi() {
        System.out.println("UserComponent Is Implements...");
    }
}
