package com.example.WheelTimer;

import java.util.concurrent.TimeUnit;

/**
 * @Author:cuijialei
 * @Date: 2019/4/23
 * @Describe
 */
public interface TimeWheelService {

    /**
     * 添加时间轮任务 - 超时关闭订单
     */
    void addTimeTaskNewOrder(int overTime, TimeUnit timeUnit, String orderNo);

}
