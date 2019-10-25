package com.zhiliao.service;

import com.zhiliao.comment.BizException;
import com.zhiliao.utils.RedisUtil;
import com.zhiliao.utils.RsaUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    /**
     * 获取密钥
     */
    public Map<String,Object> getPublicKey() throws Exception {
        KeyPair keyPair = RsaUtil.getKeyPair();
        String publicKey = RsaUtil.getPublicKey(keyPair);
        String privateKey = RsaUtil.getPrivateKey(keyPair);
        RedisUtil.setValue(publicKey,privateKey);

        //加密
        String phone="19945096907";
        byte[] bytes = RsaUtil.base642Byte(phone);
        byte[] publicEncrypt = RsaUtil.publicEncrypt(bytes, keyPair.getPublic());
        String s = RsaUtil.byte2Base64(publicEncrypt);
        System.out.println("手机号加密为："+s);

        byte[] bytes1 = RsaUtil.base642Byte(s);
        byte[] bytes2 = RsaUtil.privateDecrypt(bytes1, keyPair.getPrivate());
        String s1 = RsaUtil.byte2Base64(bytes2);
        System.out.println("解密为："+s1);
        Map<String,Object> map = new HashMap<>();
        map.put("publicKey",publicKey);
        return map;
    }

    /**
     * 获取token
     * @param ciphertext
     * @param publicKey
     */
    public Map<String,Object> getToken(String ciphertext,String publicKey){
        if (StringUtils.isEmpty(ciphertext)){
            throw new BizException("系统参数错误！");
        }
        if (StringUtils.isEmpty(publicKey)){
            throw new BizException("系统参数错误！");
        }



        return null;
    }

}
