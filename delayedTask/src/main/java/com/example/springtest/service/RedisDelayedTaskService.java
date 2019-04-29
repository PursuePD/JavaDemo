package com.example.springtest.service;

import java.util.Collection;

/**
 * @Author:cuijialei
 * @Date: 2019/4/29
 * @Describe
 */
public interface RedisDelayedTaskService {

    /**
     * 添加一个延时订单
     * @param overTime 执行时间
     * @param json 打印信息
     */
    void addTask(long overTime,String json);

    /**
     * 找到一个需要执行的任务
     * @return 该键的值
     */
    String getFirstReadyTaskAndRemove();

    /**
     * 获取可以执行的任务集合
     * @return
     */
    Collection<String> getToDoRangeTask(long endTime);

}
