package com.example.springtest.service;

/**
 * @Author:cuijialei
 * @Date: 2019/4/29
 * @Describe
 */
public interface DelayTaskService {

    /**
     * 执行任务
     */
    void doDelayTask();


    /**
     * 批量执行任务
     */
    void doDelayTaskCollection();
}
