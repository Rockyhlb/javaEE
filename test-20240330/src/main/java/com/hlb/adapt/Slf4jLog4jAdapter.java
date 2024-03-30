package com.hlb.adapt;

/**
 * @BelongsProject: test-20240330
 * @BelongsPackage: com.hlb.adapt
 * @CreateTime : 2024/3/30 22:22
 * @Description: TODO
 * @Author: code_hlb
 */
public class Slf4jLog4jAdapter implements Sl4jApi {
    private Log4j log4j;

    public Slf4jLog4jAdapter(Log4j log4j) {
        this.log4j = log4j;
    }

    @Override
    public void log(String message) {
        log4j.log(message);
    }
}
