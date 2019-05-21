package com.example.springtest.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    /**
     * 延时消息交换机
     */
    public static final String DELAY_EXCHANGE="delay_exchange";
    /**
     * 延时任务队列
     */
    public static final String DELAY_QUEUE="delay_queue";
    /**
     * 延时任务队列KEY
     */
    public static final String DELAY_ROUTING_KEY="delay_routing_key";

    @Value(value = "${spring.rabbitmq.host}")
    private String host;
    @Value(value = "${spring.rabbitmq.port}")
    private int port;
    @Value( value = "${spring.rabbitmq.virtual-host}")
    private String vhost;
    @Value(value = "${spring.rabbitmq.password}")
    private String password;
    @Value(value = "${spring.rabbitmq.username}")
    private String username;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host, this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.vhost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public CustomExchange delayExchange(){
        Map<String, Object> args = new HashMap<>(16);
        args.put("x-dead-letter-exchange", "delay_exchange");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue customsDelayQueue(){return new Queue(DELAY_QUEUE,true);}
    @Bean
    public Binding bindingCustomsDelayQueueToDelayExchange(){
        return BindingBuilder.bind(customsDelayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }
}