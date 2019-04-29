package com.example.springtest.service.impl;

import com.example.springtest.service.DelayTaskService;
import com.example.springtest.service.RedisDelayedTaskService;
import com.example.springtest.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author:cuijialei
 * @Date: 2019/4/29
 * @Describe 延时任务消费者
 */
@Service
class DelayTaskServiceImpl implements DelayTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelayTaskServiceImpl.class);

    @Autowired
    RedisDelayedTaskService redisDelayedTaskService;

    @Override
    public void doDelayTask() {
       String json = redisDelayedTaskService.getFirstReadyTaskAndRemove();
       if(!StringUtils.isEmpty(json)){
           LOGGER.info("当前时间{}，{}",DateUtils.getStringTime(System.currentTimeMillis()),json );
       }
    }
}
