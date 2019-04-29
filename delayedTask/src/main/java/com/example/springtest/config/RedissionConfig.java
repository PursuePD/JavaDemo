package com.example.springtest.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedissionConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${singleServerConfig.address}")
    private String address;

    @Value("${singleServerConfig.password}")
    private String password;

    @Value("${singleServerConfig.database}")
    private int database;

    @Bean
    public RedissonClient createClient(){

        //Jedis jedis = new Jedis("127.0.0.1");

        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setClientName(applicationName);
        singleServerConfig.setAddress(address);
//        singleServerConfig.setPassword(password);
        singleServerConfig.setDatabase(database);
        return Redisson.create(config);
    }

}