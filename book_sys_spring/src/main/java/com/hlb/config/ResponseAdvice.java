package com.hlb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlb.model.Result;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @BelongsProject: book_sys_spring
 * @BelongsPackage: com.hlb.config
 * @CreateTime : 2024/3/30 22:44
 * @Description: 统一数据返回格式
 * @Author: code_hlb
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 判断对哪些请求进行处理. true-进行处理, false-不处理.
        return true;
    }

    // 对返回的 response 进行具体处理
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 当 body 已经被包装过就无需再次包装了
        if (body instanceof Result) {
            return body;
        }
        // 当返回结果是String类型时，出现lang.ClassCastException: com.hlb.model.Result cannot be cast to java.lang.String
        // 解决方法，调用SpringBoot内置的Jackson方法完成信息的序列化
        if (body instanceof String) {
            return objectMapper.writeValueAsString(Result.success(body));
        }
        return Result.success(body);
    }
}
