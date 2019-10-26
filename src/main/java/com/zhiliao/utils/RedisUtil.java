package com.zhiliao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
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
        myRedisTemplate.opsForValue().set(key,value,time, TimeUnit.HOURS);
    }

    /**
     * 存储hash数据
     */
    public static void setHash(String name,String key,String value){
        myRedisTemplate.opsForHash().put(name,key,value);
    }

    /**
     * 获取hash数据
     */
    public static Map<Object, Object> getHash(String name){
        return myRedisTemplate.opsForHash().entries(name);
    }

    /**
     * key是否存在
     */
    public static boolean hasKey(String key){
        return myRedisTemplate.hasKey(key);
    }

}
