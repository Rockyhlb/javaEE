package com.hlb.blog;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog
 * @CreateTime : 2024/4/12 14:23
 * @Description: TODO
 * @Author: code_hlb
 */
public class JWTUtilsTest {
    // 设置过期时间为1min
    private static final long EXPIRATION_TIME = 60 * 1000;
    // 密钥
    private static final String secretString = "ws/WMhpfsCr8OSZl30589TkOo3Q0BFvpmoglfTGpso0=";
    // 生成安全密钥
    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

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
    @Test
    public void genToken() {
        Map<String,Object> claim = new HashMap<>();
        claim.put("id","3");
        claim.put("name","zhangsan");
        // JwtBuilder addClaims(Map<String, Object> var1);
        String token = Jwts.builder()
                .addClaims(claim)  // 负载
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)  // 签名算法
                .compact();
        System.out.println(token);
    }

    // 生成密钥
    @Test
    public void genkey() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
    }

    // 校验令牌
    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbmdzYW4iLCJpZCI6IjMiLCJleHAiOjE3MTI5MDY4NzF9.8LZnbIRoyGjv8bhOKgoDmzFamXLVvc7uGKSQ379_JVQ";
        // 创建解析器，设置签名密钥
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
        // 解析token
        Claims body = null;
        try {
            body = parser.parseClaimsJws(token).getBody();
        }catch (Exception e) {
            System.out.println("令牌校验失败！");
        }
        System.out.println(body);
    }
}
