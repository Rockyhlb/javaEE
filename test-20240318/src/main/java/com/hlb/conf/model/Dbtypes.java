package com.hlb.conf.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: test-20240318
 * @BelongsPackage: com.hlb.conf.model
 * @CreateTime : 2024/3/18 15:04
 * @Description: TODO
 * @Author: code_hlb
 */
@Data
@Component
@ConfigurationProperties(prefix = "dbtypes")
public class Dbtypes {
    private List<String> name;
    private Map<String,String> map;
}
