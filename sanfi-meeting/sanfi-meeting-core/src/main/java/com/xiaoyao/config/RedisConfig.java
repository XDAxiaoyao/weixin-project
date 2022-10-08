package com.xiaoyao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author guoweixin
 * @Date 2022/10/8
 * @Version 1.0
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        //String key keySerializer
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        //String value valueSerializer
        template.setValueSerializer(stringRedisSerializer);

        // Hash key hashKeySerializer
        template.setHashKeySerializer(stringRedisSerializer);
        // Hash value hashValueSerializer  (默认JDK）可以方便的让你存取一个对象。还希望在客户端中能看清对像样子甚至可以在客户端修改。
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer();
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        return template;
    }

}
