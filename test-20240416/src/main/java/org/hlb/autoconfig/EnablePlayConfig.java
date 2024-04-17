package org.hlb.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 由第三方依赖提供注解进行封装
 */
@Target(ElementType.TYPE)
@Import(MyImporterSelector.class)  // 指定要导入的类
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePlayConfig {

}
