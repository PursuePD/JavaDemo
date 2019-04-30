package com.example.springtest.service.impl;

import com.example.springtest.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author:cuijialei
 * @Date: 2019/4/30
 * @Describe
 */
@Component
public class RedisMessageListener  implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageListener.class);


    @Override
    public void onMessage(Message message, byte[] bytes) {
        LOGGER.info("监听Topic：{}，key:{}已过期，当前时间{}", new String(bytes), message.toString(),DateUtils.getStringTime(System.currentTimeMillis()));
    }
}
