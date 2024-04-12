package com.hlb.blog.utils;

import com.hlb.blog.constants.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog
 * @CreateTime : 2024/4/12 14:23
 * @Description: JWT 工具类
 * @Author: code_hlb
 */
@Slf4j
public class JWTUtils {
    /**
     * 在传统登陆验证成功以后，生成session存储在服务器内存中，任何再将sessionId存储在客户端Cookie中，
     * 这种方法不适用于集群环境下(一般项目都是部署在多台服务器上，任何通过负载均衡等手段将请求分发到不同的服务器上)，
     * 令牌技术：当下最流行的会话跟踪手段
     * 令牌：一个用户身份的标识，本质上就是一个字符串，且服务器具备生成令牌和验证令牌的能力
     * 令牌的优缺点：
     * 1、解决了集群环境下用户的认证问题
     * 2、减轻了服务器的存储压力(令牌可以直接存储在客户端中)
     * 3、缺点：需要自己实现(令牌的生成，令牌的传递，令牌的校验)
     * JWT: JSON Web Token, ⽤于客户端和服务器之间传递安全可靠的信息，其本质是⼀个token, 是⼀种紧凑的URL安全⽅法.
     * JWT由三部分组成：Header(头部，包括令牌类型及使用的Hash算法).Payload(负载，存储有效信息，但是不建议存储敏感信息，因为会被解析得出).Signature(签名，防止内容被篡改)
     * JWT使用的base64是编码方式，而不是加密方式
     */
    // 设置过期时间为1h
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;
    // 密钥，用于对令牌进行签名和验证,这个密钥是一个Base64编码的字符串。
    private static final String secretString = "ws/WMhpfsCr8OSZl30589TkOo3Q0BFvpmoglfTGpso0=";
    // 安全密钥：使用JJWT库中的Keys.hmacShaKeyFor方法，将Base64编码的密钥转换为Key对象
    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    /**
     * 生成令牌
     */
    public static String genToken(Map<String, Object> claim) {
        // JwtBuilder addClaims(Map<String, Object> var1);
        return Jwts.builder()
                .addClaims(claim)  // 负载
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)  // 签名算法
                .compact();
    }

    /**
     * 解析令牌
     */
    public static Claims parseToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
        Claims body = null;
        try {
            body = parser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.error("令牌过期, token:{}", token);
        } catch (Exception e) {
            log.error("校验失败, token:{}", token);
        }
        return body;
    }

    /**
     * 校验令牌
     */
    public static boolean checkToken(String token) {
        Claims claims = parseToken(token);
        return claims != null;
    }

    /**
     * 从令牌中获取用户ID
     */
    public static Integer getUserIdFromToken(String token) {
        Claims body = parseToken(token);
        if (body != null) {
            return (Integer) body.get(Constant.USER_CLAIM_ID);
        }
        return null;
    }
}
