package com.zhiliao.controller;

import com.zhiliao.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestGetRedis {

/*    private static RedisTemplate<Object, Object> myRedisTemplate = null;

    @Autowired
    public TestGetRedis(@Qualifier("myRedisTemplate") RedisTemplate<Object, Object> myRedisTemplate) {
        TestGetRedis.myRedisTemplate = myRedisTemplate;
    }

    @GetMapping(value = "/getKey")
    public void getKey(){
        String key="name2";
        String s = String.valueOf(myRedisTemplate.opsForValue().get(key));
        System.out.println(s);
        String s1 = String.valueOf(myRedisTemplate.opsForHash().get("name1", "snica"));
        System.out.println(s1);
    }*/

    @GetMapping(value = "/setValue")
    public void setValue(){
        RedisUtil.setValue("name4","22222");
        System.out.println(1);
    }
}
