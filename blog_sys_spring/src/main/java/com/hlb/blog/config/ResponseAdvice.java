package com.hlb.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlb.blog.model.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.config
 * @CreateTime : 2024/4/11 15:08
 * @Description: 统一结果返回
 * @Author: code_hlb
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 对哪些接口进行管理
        return true;
    }

    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            // 当返回结果已经是Result时
            return body;
        }
        if (body instanceof String) {
            // 通过writeValueAsString 完成数据的序列化
            return objectMapper.writeValueAsString(Result.success(body));
        }
        return Result.success(body);
    }
}
