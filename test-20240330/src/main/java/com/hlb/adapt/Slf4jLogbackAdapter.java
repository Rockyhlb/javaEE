package com.hlb.adapt;

/**
 * @BelongsProject: test-20240330
 * @BelongsPackage: com.hlb.adapt
 * @CreateTime : 2024/3/30 22:17
 * @Description: Slf4j 通过调用 Logback 的适配器，实现日志的打印
 * @Author: code_hlb
 */
public class Slf4jLogbackAdapter implements Sl4jApi {
    private LogBack logBack;

    public Slf4jLogbackAdapter(LogBack logBack) {
        this.logBack = logBack;
    }

    @Override
    public void log(String message) {
        logBack.info(message);
    }
}
