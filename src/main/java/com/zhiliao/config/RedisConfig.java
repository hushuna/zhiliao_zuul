package com.zhiliao.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {


    @Bean(name="genericJackson2JsonRedisSerializer")
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean(name = "myRedisTemplate")
    public RedisTemplate<Object,Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory, GenericJackson2JsonRedisSerializer ser){
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //设置序列化方法
        template.setDefaultSerializer(ser);
        return template;
    }

}
