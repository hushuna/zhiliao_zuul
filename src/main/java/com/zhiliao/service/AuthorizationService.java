package com.zhiliao.service;

import com.zhiliao.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

/**
 * @author: hsn
 * @description:
 * @date: 2019/10/17 14:20
 **/
@Service
public class AuthorizationService {

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
        return publicKeyNoRepeat;
    }



}
