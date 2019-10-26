package com.zhiliao.controller;

import com.zhiliao.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/test")
public class TestGetRedis {

    @GetMapping(value = "/setValue")
    public void setValue(){
        /*RedisUtil.setValue("name4","22222");
        System.out.println(1);*/
        Map<Object, Object> name1 = RedisUtil.getHash("name1");
        System.out.println(name1);
        Set<Object> objects = name1.keySet();
        for (Object object : objects) {

        }
    }


}
