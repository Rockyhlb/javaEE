package com.hlb.principle;

import org.hlb.autoconfig.EnablePlayConfig;
import org.hlb.autoconfig.MyImporterSelector;
import org.hlb.autoconfig.PlayConfig;
import org.hlb.autoconfig.PlayConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

//@ComponentScan("org.hlb.autoconfig")
//@ComponentScan({"org.hlb.autoconfig","com.hlb.principle"})
//@Import({PlayConfig.class, PlayConfig2.class})
//@Import(MyImporterSelector.class)
@EnablePlayConfig
@SpringBootApplication
public class Test20240416Application1 {
    public static void main(String[] args) {
        // 从 Spring容器中获取 Bean
        ApplicationContext context = SpringApplication.run(Test20240416Application1.class, args);
        /**
         * 获取org.hlb.autoconfig的PlayConfig这个Bean,出现 “NoSuchBeanDefinitionException”
         * 这是由于 Spring的无法注解和方法注解帮助我们把Bean加载到IOC容器的前提是 ‘注解类需要和启动类在同一个目录下’
         * 解决方案：
         * 1、@ComponentScan 指定扫描路径 (可以指定多个路径)
         * 2、@Import:
         *    2.1 导入类: @Import(PlayConfig.class)
         *    2.2 导入接口实现类: @Import(MyImporterSelector.class)
         * 3、由第三方依赖提供注解，这个注解一般都以@EnableXxxx开头的注解,注解中封装的就是 @Import 注解 (SpringBoot采用的方式)
         * <p>
         * 当SpringBoot程序启动时，会加载配置文件当中所定义的配置类，通过@Import注解将这些配置类全部加载到Spring的IOC容器中进行管理
         * @SpringBootApplication --> @SpringBootConfiguration --> @Configuration (标志当前类为配置类)
         *                        --> @EnableAutoConfiguration --> @AutoConfigurationPackage (自动导入配置类包)
         *                                                     --> @Import({AutoConfigurationImportSelector.class})  (导入配置文件，有选择的导入配置类)
         *                        --> @ComponentScan (包扫描)
         */
        context.getBean(PlayConfig.class).use();
        context.getBean(PlayConfig2.class).use();
    }
}
