package com.javademo.redislock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author:cuijialei
 * @Date: 2019/7/24
 * @Describe
 */
public class Config {
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${redisson.address}")
    private String address;
    @Value("${spring.redis.database}")
    private String database;
    @Value("${spring.redis.password}")
    private String password;


    @Primary
    @Bean
    public RedisTemplate redisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }

    @Bean
    public RedissonClient redissonClient(){
        org.redisson.config.Config config = new org.redisson.config.Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setClientName("clientName");
        singleServerConfig.setAddress(address);
        singleServerConfig.setDatabase(Integer.valueOf(database));
        return Redisson.create(config);
    }
}
