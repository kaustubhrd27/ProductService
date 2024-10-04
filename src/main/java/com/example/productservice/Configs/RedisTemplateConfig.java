package com.example.productservice.Configs;

import com.example.productservice.Models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
// why config -->> to have single object for whole project
// annotation indicates that this class contains one or more bean definitions
// This class, marked as @Configuration, will be scanned during the application startup,
// land Spring will recognize that it should create and manage the beans defined within it.
public class RedisTemplateConfig {
    @Bean
    public RedisTemplate<String, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}

// so this will create single bean of this
