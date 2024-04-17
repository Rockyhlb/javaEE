package org.hlb.autoconfig;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @BelongsProject: test-20240416
 * @BelongsPackage: org.hlb.autoconfig
 * @CreateTime : 2024/4/17 14:56
 * @Description: TODO
 * @Author: code_hlb
 */
public class MyImporterSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 需要导入的全限定类名
        return new String[] {"org.hlb.autoconfig.PlayConfig","org.hlb.autoconfig.PlayConfig2"};
    }
}
