package com.example.springtest.service.impl;

import com.example.springtest.config.RabbitMqConfig;
import com.example.springtest.service.DelayMassageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:cuijialei
 * @Date: 2019/5/5
 * @Describe
 */
@Service
public class DelayMassageServiceImpl implements DelayMassageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelayMassageListener.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendDelayMessage(String msg,long outDueTime) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE, RabbitMqConfig.DELAY_ROUTING_KEY, msg, message -> {
            message.getMessageProperties().setHeader("x-delay", outDueTime);
            LOGGER.info("发送延时消息:"+message);
            return message;
        });
    }
}
