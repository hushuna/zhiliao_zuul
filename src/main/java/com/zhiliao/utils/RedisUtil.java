package com.zhiliao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    private static RedisTemplate<Object, Object> myRedisTemplate;

    /**
     * 根据key获取缓存的值
     * @param key
     */
    public static String get(String key){
        return (String) myRedisTemplate.opsForValue().get(key);
    }
}
