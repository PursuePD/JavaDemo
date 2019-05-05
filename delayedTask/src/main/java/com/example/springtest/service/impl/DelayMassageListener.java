package com.example.springtest.service.impl;

import com.example.springtest.config.RabbitMqConfig;
import com.example.springtest.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Author:cuijialei
 * @Date: 2019/5/5
 * @Describe 延时任务监听
 */
@Component
public class DelayMassageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelayMassageListener.class);

    /***
     *@description 监听任务延时消息，获取延时任务
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitMqConfig.DELAY_QUEUE)
    public void customsDelayMessage(String msg) throws IOException {
        LOGGER.info("当前时间{}，收到任务 {}",DateUtils.getStringTime(System.currentTimeMillis()),msg);
    }
}
