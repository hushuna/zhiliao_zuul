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
    public void getPublicKey() throws Exception {
        KeyPair keyPair = RsaUtil.getKeyPair();
        String publicKey = RsaUtil.getPublicKey(keyPair);
        String privateKey = RsaUtil.getPrivateKey(keyPair);
        String publicKey1 = publicKey.replaceAll("\r\n", "");
        String privateKey1 = privateKey.replaceAll("\r\n", "");
        RedisUtil.setHash("key",publicKey1,privateKey1);
    }

    /**
     * 获取token
     */
    public Map<String,Object> getToken() throws Exception {
        boolean hasKey = RedisUtil.hasKey("key");
        Integer i = (int) (Math.random() * 9 + 1) * 100000;
        String text = String.valueOf(i);
        Map<String,Object> map = new HashMap<>();
        if (hasKey){
            return generateToken(text, map);
        }else {
            getPublicKey();
            return generateToken(text,map);
        }
    }

    /**
     * 生成token
     * @param text
     * @param map
     * @return
     * @throws Exception
     */
    private Map<String, Object> generateToken(String text, Map<String, Object> map) throws Exception {
        Map<Object, Object> key = RedisUtil.getHash("key");
        String publicKey="";
        String privateKey="";
        for (Object o : key.keySet()) {
            publicKey = o.toString();
            privateKey = key.get(o).toString();
        }
        byte[] bytes = text.getBytes();
        String s = RsaUtil.byte2Base64(bytes);
        byte[] bytes1 = RsaUtil.base642Byte(s);
        PublicKey publicKey1 = RsaUtil.string2PublicKey(publicKey);
        byte[] bytes2 = RsaUtil.publicEncrypt(bytes1, publicKey1);
        String token = RsaUtil.byte2Base64(bytes2);
        token = publicKey.replaceAll("\r\n", "");
        token = privateKey.replaceAll("\n", "");
        token = privateKey.replaceAll("\\+", "");
        RedisUtil.setValue(token,token);
        RedisUtil.setTime(token,token, (long) 3);
        map.put("token",token);
        return map;
    }

}
