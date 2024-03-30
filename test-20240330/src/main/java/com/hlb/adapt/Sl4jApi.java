package com.hlb.adapt;

/**
 * @BelongsProject: test-20240330
 * @BelongsPackage: com.hlb.adapt
 * @CreateTime : 2024/3/30 22:05
 * @Description: Slf4j 接口
 * @Author: code_hlb
 */
public interface Sl4jApi {
    /**
     * 适配器模式：也叫包装器模式. 可以把两个不兼容的接口通过⼀定的⽅式使之兼容
     * slf4j 就使⽤了适配器模式, slf4j提供了⼀系列打印⽇志的api, 底层调⽤的是log4j 或者
     * logback来打印志,我们作为调用者, 只需要调⽤slf4j的api就⾏了。
     * <p>
     * ⼀般来说，适配器模式可以看作是一种"补偿模式"，用来补救设计上的缺陷，
     * 如果在设计初期，我们就能协调规避接口不兼容的问题, 就不需要使用适配器模式了
     */
    void log(String message);
}
