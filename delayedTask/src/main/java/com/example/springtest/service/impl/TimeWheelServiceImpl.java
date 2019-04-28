package com.example.springtest.service.impl;

import com.example.springtest.service.LoggerService;
import com.example.springtest.service.TimeWheelService;
import com.example.springtest.util.DateUtils;
import io.netty.util.HashedWheelTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Date: 2019/4/23
 * @Describe 时间轮不能创建多个会调至CUP占用过高，统一使用一个往里面添加任务。
 */
@Service
public class TimeWheelServiceImpl implements TimeWheelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeWheelServiceImpl.class);

    @Autowired
    private LoggerService loggerService;
    /**
     *  long tickDuration, // tick的时长，也就是指针多久转一格
     *  TimeUnit unit, // tickDuration的时间单位
     *  int ticksPerWheel, // 一圈有几格
     *  boolean leakDetection // 是否开启内存泄露检测
     */
    private static final HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS,60);


    @Override
    public void addTimeTaskNewOrder(int overTime, TimeUnit timeUnit, String orderNo) {
        hashedWheelTimer.newTimeout(timeout -> {
            loggerService.printInfo("系统自动关闭订单");
        }, overTime, timeUnit);
    }
}
