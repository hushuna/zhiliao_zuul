package com.zhiliao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestGetRedis {

    @Autowired
    private static RedisTemplate<Object, Object> myRedisTemplate;

    @GetMapping(value = "/getKey")
    public String getKey(){
        String key="name";
        String s = (String) myRedisTemplate.opsForValue().get(key);
        System.out.println(s);
        return s;
    }
}
