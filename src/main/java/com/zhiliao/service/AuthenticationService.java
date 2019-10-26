package com.zhiliao.service;

import com.zhiliao.comment.BizException;
import com.zhiliao.utils.RedisUtil;
import com.zhiliao.utils.RsaUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
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
        String publicKey1 = publicKey.replaceAll("\r\n", "");
        String privateKey1 = privateKey.replaceAll("\r\n", "");
        RedisUtil.setValue(publicKey1,privateKey1);

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
    public Map<String,Object> getToken(String ciphertext,String publicKey) throws Exception {
        if (StringUtils.isEmpty(ciphertext)){
            throw new BizException("系统参数错误！");
        }
        if (StringUtils.isEmpty(publicKey)){
            throw new BizException("系统参数错误！");
        }
        Map<String,Object> map = new HashMap<>();
        String privateKey = RedisUtil.get(publicKey);
        //获取私钥
        PrivateKey privateKey1 = RsaUtil.string2PrivateKey(privateKey);
        //解密
        byte[] bytes = RsaUtil.base642Byte(ciphertext);
        byte[] bytes1 = RsaUtil.privateDecrypt(bytes, privateKey1);
        String s = RsaUtil.byte2Base64(bytes1);
        if (s.length()!=11){
            //解密失败   页面跳转到登陆页面
            map.put("token",0);
            return map;
        }
        KeyPair keyPair = RsaUtil.getKeyPair();
        String publicKey1 = RsaUtil.getPublicKey(keyPair);
        PublicKey publicKey2 = RsaUtil.string2PublicKey(publicKey1);
        byte[] bytes2 = RsaUtil.base642Byte(s);
        byte[] bytes3 = RsaUtil.publicEncrypt(bytes2, publicKey2);
        String token = RsaUtil.byte2Base64(bytes3);
        token = token.replaceAll("\r\n", "");
        token = token.replaceAll("\n", "");
        token = token.replaceAll("\\+", "");
        RedisUtil.setValue(token,token);
        map.put("token",token);
        return map;
    }

}
