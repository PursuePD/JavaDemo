package com.example.mail.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author:cuijialei
 * @Date: 2018/9/6
 * @Describe 定时任务
 *
 * 定时任务：
 * 定时任务需要再启动类注解：@EnableScheduling
 *
 */
@Component
public class ScheduledService {
    private final Logger logger = LoggerFactory.getLogger(ScheduledService.class);

    /**
     * corn 表达式 用于定时
     *  便捷生成网址 http://cron.qqe2.com/
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        logger.info("这是一个定时任务 ，每5秒一次");
    }
}
