package com.example.springtest.config;



import com.example.springtest.service.impl.RedisMessageListener;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;


import java.util.Objects;


@Configuration
public class RedisMessageListenerrConfig {

    private final RedisTemplate<Object, Object> redisTemplate;
    private final RedisMessageListener messageListener;

    @Autowired
    public RedisMessageListenerrConfig(RedisTemplate<Object, Object> redisTemplate, RedisMessageListener messageListener) {
        this.redisTemplate = redisTemplate;
        this.messageListener = messageListener;
    }

    @Bean
    public RedisMessageListenerContainer configRedisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // 设置Redis的连接工厂
        container.setConnectionFactory(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        // 设置监听的Topic __keyevent@0__:expired 表示监听db0的过期事件
        ChannelTopic channelTopic = new ChannelTopic("__keyevent@0__:expired");
        // 设置监听器
        container.addMessageListener(messageListener, channelTopic);
        return container;
    }
}
