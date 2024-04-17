package org.hlb.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: test-20240416
 * @BelongsPackage: org.hlb.autoconfig
 * @CreateTime : 2024/4/17 14:22
 * @Description: TODO
 * @Author: code_hlb
 */
@Slf4j
@Component
public class PlayConfig {
    /**
     * SpringBoot⾃动配置, 就是指SpringBoot是如何将依赖jar包中的配置类以及Bean加载到Spring IoC容器中的.
     * 手动创建org.hlb.autoconfig，模拟引入第三方jar包
     */
    public void use() {
        log.info("Start play..");
    }
}
