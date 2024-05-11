package com.hlb.encrypt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: encrypt
 * @BelongsPackage: com.hlb.encrypt.controller
 * @CreateTime : 2024/5/11 14:47
 * @Description: TODO
 * @Author: code_hlb
 */
@Slf4j
@RestController
public class EncryptController {
    /**
     * 密码算法：
     * 1、对称密码算法：加密密钥和解密密钥相同的算法，常见的有AES/DES/3DES/RC4/RC5等.
     * 2、非对称密码算法：加密密钥和解密密钥不同的密码算法，该算法使用一个密钥进行加密(公钥)，用另外一个密钥进行解密(私钥)，常见的有RSA/DSA/ECDSA等.
     * 3、摘要算法：把任意长度的输入信息转化为固定长度的输出数据，摘要算法是不可逆的，因此无法进行解密，常见的有MD5/SHA系列/CRC等.
     * 摘要算法的验证：由于同样的明文经过相同的摘要算法后生成的密文是相同的，因此就可以基于此进行验证(如果密文一样，就可认为明文一样)
     */
    private static final Map<String, String> keyMap = new HashMap<>();

    /*
     * 加密逻辑：
     * 1、获取输入
     * 2、通过MD5加密,并将明文和密文存放入Map中
     * 3、返回加密后的字符串
     * */
    @RequestMapping("/encrypt")
    public static String encrypt(String inputText) {
        // 校验输入
        if (0 == inputText.trim().length()) {
            log.error("输入为空！");
            return "";
        }
        // 密文内容：明文经过 md5 加密后的字符串
        String securityText = DigestUtils.md5DigestAsHex(inputText.getBytes()).toUpperCase();
        // 将 明文(Value) 和 密文(Key) 一起存放到Map中，方便后续“解密”
        keyMap.put(securityText, inputText);
        log.info("encrypt,Key:{}, Value:{}", securityText, inputText);
        return securityText;
    }

    /*
     * 解密逻辑：
     * 1、获取输入
     * 2、根据 value 获取 key
     * */
    @RequestMapping("/decrypt")
    public static String decrypt(String inputText) {
        if (inputText == null || inputText.length() != 32) {
            String err = "输入的密文格式不对，无法解析！";
            log.error(err);
            return "输入的密文格式不对，无法解析！";
        }
        // 返回明文
        String plaintext = keyMap.get(inputText);
        if (plaintext == null) {
            log.error("发生内部错误，请联系客服~~");
            return "";
        }
        log.info("decrypt,Key:{}, Value:{}", plaintext, inputText);
        return plaintext;
    }
}