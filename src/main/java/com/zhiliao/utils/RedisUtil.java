package com.zhiliao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static RedisTemplate<Object, Object> myRedisTemplate = null;

    @Autowired
    public RedisUtil(@Qualifier("myRedisTemplate") RedisTemplate<Object, Object> myRedisTemplate) {
        RedisUtil.myRedisTemplate = myRedisTemplate;
    }

    /**
     * 根据key获取缓存的值
     * @param key
     */
    public static String get(String key){
        return (String) myRedisTemplate.opsForValue().get(key);
    }

    /**
     * 向缓存中存储数据
     * @param key
     * @param value
     */
    public static void setValue(Object key,Object value){
        myRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置有效时间
     */
    public static void setTime(Object key,Object value,Long time){
        myRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

}
