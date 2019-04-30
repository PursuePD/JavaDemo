package com.example.springtest.service.impl;

import com.example.springtest.service.DelayTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Author:cuijialei
 * @Date: 2019/4/29
 * @Describe
 */
@Component
public class DoTaskSchedule implements SchedulingConfigurer {

    @Autowired
    DelayTaskService delayTaskService;

    @Scheduled(cron="0/1 * * * * ?")
    public void executeFileDownLoadTask() {
        delayTaskService.doDelayTaskCollection();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean()
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
