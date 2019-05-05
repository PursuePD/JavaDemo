package com.example.springtest.service;

/**
 * @Author:cuijialei
 * @Date: 2019/5/5
 * @Describe
 */
public interface DelayMassageService {
    /**
     * 发送延时任务
     * @param msg 消息
     * @param outDueTime 过期时间
     */
    void sendDelayMessage(String msg,long outDueTime);


}
