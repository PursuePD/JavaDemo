package com.example.mail.service.impl;

import com.example.mail.event.MessageEvent;
import com.example.mail.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Author:cuijialei
 * @Date: 2018/9/6
 * @Describe 事件的监听
 *
 * 本类用于触发监听机制
 * 触发需要实现 ApplicationContextAware 用于引入ApplicationContext
 *
 */
@Service
public class EventServiceImpl implements EventService,ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(ApplicationContextAware.class);

    @Autowired
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 触发事件
     * @param msg 消息
     */
    @Override
    public boolean triggerMessage(String msg) {
        logger.info("---触发事件---");
        //创建事件源
        MessageEvent messageEvent = new MessageEvent(this, msg);
        //触发event
        this.context.publishEvent(messageEvent);
        return true;
    }
}
