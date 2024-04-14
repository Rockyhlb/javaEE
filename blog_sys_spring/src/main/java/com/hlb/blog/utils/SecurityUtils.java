package com.hlb.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @BelongsProject: blog_sys_spring
 * @BelongsPackage: com.hlb.blog.utils
 * @CreateTime : 2024/4/14 18:27
 * @Description: 加密
 * @Author: code_hlb
 */
@Slf4j
public class SecurityUtils {
    /**
     * 密码算法：
     * 1、对称密码算法：加密密钥和解密密钥相同的算法，常见的有AES/DES/3DES/RC4/RC5等.
     * 2、非对称密码算法：加密密钥和解密密钥不同的密码算法，该算法使用一个密钥进行加密(公钥)，用另外一个密钥进行解密(私钥)，常见的有RSA/DSA/ECDSA等.
     * 3、摘要算法：把任意长度的输入信息转化为固定长度的输出数据，摘要算法是不可逆的，因此无法进行解密，常见的有MD5/SHA系列/CRC等.
     * 摘要算法的验证：由于同样的明文经过相同的摘要算法后生成的密文是相同的，因此就可以基于此进行验证(如果密文一样，就可认为明文一样)
     * <p>
     * 该系统采用md5摘要算法,对传入的参数进行加密：
     * 由于摘要算法相同密文则相同明文的特性，我们需要针对此进行优化：即使是相同的明文，也需要对其包装形成不同的密文
     * 盐值(salt)：通过将密码拼接一个随机字符串的方式进行加密，这个随机字符串就是“salt”, 明文 + salt = 复杂的明文
     */
    public static String encrypt(String password) {
        /*
         * 加密逻辑(用户注册)：
         * 1、生成随机盐值
         * 2、拼接随机salt+用户明文密码,然后通过MD5加密
         * 3、保存随机盐值和密文
         * */
        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());
        // 随机生成内容不同，但是固定长度为 32 的盐值
        String salt = UUID.randomUUID().toString().replace("-", "");
        // 密文内容：盐值+明文拼接后的字符串，再进行md5加密后的字符串
        String securityPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        // salt+密文 一起存储在数据库中
        return salt + securityPassword;
    }

    public static Boolean verify(String sqlPassword, String inputPassword) {
        /*
         * 解密逻辑：
         * 1、获取用户注册时的随机盐值
         * 2、拼接待验证的明文+第一步得到的随机盐值，最后MD5得到密文
         * 3、判断第二步得到的密文和用户注册时数据库中存的密文是否一致
         * */
        if (sqlPassword == null || sqlPassword.length() != 64) {
            log.error("数据库中存储的密码格式不对！");
            return false;
        }
        String salt = sqlPassword.substring(0, 32);
        String secretPassword = DigestUtils.md5DigestAsHex((salt + inputPassword).getBytes());
        return sqlPassword.equals(salt + secretPassword);
    }
}
