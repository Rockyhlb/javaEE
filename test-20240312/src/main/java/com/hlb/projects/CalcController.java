package com.hlb.projects;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240312
 * @BelongsPackage: com.hlb.projects
 * @CreateTime : 2024/3/12 12:00
 * @Description: TODO
 * @Author: code_hlb
 */
@RequestMapping("/calc")
@RestController
public class CalcController {
    // 计算器接口定义：
    // URL: /calc/sum
    // 参数：num1, num2
    // 返回：两数之和
    @RequestMapping("/sum")
    public String sum(Integer num1,Integer num2) {
        // System.out.println("==============");
        Integer sum = num1 + num2;
        return "计算结果：" + sum;
    }
}
