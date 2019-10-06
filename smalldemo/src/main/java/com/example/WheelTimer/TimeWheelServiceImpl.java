package com.example.WheelTimer;

import io.netty.util.HashedWheelTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Date: 2019/4/23
 * @Describe 时间轮不能创建多个会调至CUP占用过高，统一使用一个往里面添加任务。
 */
@Service
public class TimeWheelServiceImpl implements TimeWheelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeWheelServiceImpl.class);

    /**
     * 时间的间隔
     * 间隔的单位
     */
    private static HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.SECONDS);


    @Override
    public void addTimeTaskNewOrder(int overTime, TimeUnit timeUnit, String orderNo) {
        hashedWheelTimer.newTimeout(timeout -> {
//            LOGGER.info("自动关闭订单：{} 关闭时间：{}" , orderNo , DateUtils.getStringTime(System.currentTimeMillis()));
            //TODO: 执行订单关闭方法
            //Service.closeOrder(orderNo,"系统自动关闭订单");
        }, overTime, timeUnit);
    }
}
