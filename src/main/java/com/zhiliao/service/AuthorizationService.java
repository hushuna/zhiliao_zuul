package com.zhiliao.service;

import com.zhiliao.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: hsn
 * @description:
 * @date: 2019/10/17 14:20
 **/
@Service
public class AuthorizationService {

    private static final String CONSTANT_WEB = "web";

    private static final String CONSTANT_ANDROID = "and";

    private static final String CONSTANT_IOS = "ios";

    private static final Integer CONSTANT_SUB_INDEX = 3;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 获取公钥,并储存到redis中
     */
    public String getPublicKey() throws Exception {
        // 产生密钥对
        KeyPair keyPair = RsaUtil.getKeyPair();
        // 获取公钥
        String publicKeyStr = RsaUtil.getPublicKey(keyPair);
        // 获取私钥
        String privateKeyStr = RsaUtil.getPrivateKey(keyPair);
        // 字符串去空
        String publicKeyNoRepeat = publicKeyStr.trim().replaceAll("\n", "");
        String publicValueNoRepeat = privateKeyStr.trim().replaceAll("\n", "");
        System.out.println(publicKeyNoRepeat);
        System.out.println(publicValueNoRepeat);
        // 公钥为key ,value为私钥
        Cache cache = cacheManager.getCache("publickey");
        cache.put(publicKeyNoRepeat, publicValueNoRepeat);

        return publicKeyNoRepeat;
    }

    /**
     * 获取token
     *
     * @param secretMessage 密文
     * @param publicKey     公钥
     * @return String 返回token
     * @throws Exception
     */
    public String getToken(String secretMessage, String publicKey) throws Exception {
        Cache cache = cacheManager.getCache("publickey");
        // 获取私钥
        String secretKey = cache.get(publicKey, String.class);

        // 利用私钥生成工具key
        PrivateKey privateKey = RsaUtil.string2PrivateKey(secretKey);

        // 对密文进行解密
        // 获取解密后的原文信息
        byte[] base642Byte = RsaUtil.base642Byte(secretMessage);
        byte[] decrypt = RsaUtil.privateDecrypt(base642Byte, privateKey);
        String message = new String(decrypt);

        // 首先需要判断解密后的长度
        if (message.length() > CONSTANT_SUB_INDEX) {
            // 对解密后的message进行加解密...
            String newMessage = message.substring(message.length() - 3, message.length());
            if (!(CONSTANT_WEB.equals(newMessage) || CONSTANT_ANDROID.equals(newMessage)||CONSTANT_IOS.equals(newMessage))) {
                throw new Exception();
            }
        }

        // 根据message获取新的token
        // 生成新的密钥对
        KeyPair keyPair = RsaUtil.getKeyPair();

        // 获取新公钥
        String publicKeyStr = RsaUtil.getPublicKey(keyPair);
        PublicKey newPublicKey = RsaUtil.string2PublicKey(publicKeyStr);
        byte[] publicEncrypt = RsaUtil.publicEncrypt(message.getBytes(), newPublicKey);
        String token = RsaUtil.byte2Base64(publicEncrypt);

        // 把token存在redis中
        Cache tokenCache = cacheManager.getCache("token");
        token = token.trim().replaceAll("\r\n", "");
        token = token.trim().replaceAll("\n", "");
        token = token.trim().replaceAll("\\+", "");
        tokenCache.put(token, token);
        return token;
    }

}
