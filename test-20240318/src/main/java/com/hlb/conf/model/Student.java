package com.hlb.conf.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.model
 * @CreateTime : 2024/3/18 15:00
 * @Description: TODO
 * @Author: code_hlb
 */
@Component
@ConfigurationProperties(prefix = "student")
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
