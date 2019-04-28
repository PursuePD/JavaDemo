package com.example.springtest.service;


import com.example.springtest.DelayedTaskApplication;
import com.example.springtest.service.impl.TimeWheelServiceImpl;
import com.example.springtest.util.DateUtils;
import io.netty.util.HashedWheelTimer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


/**
 * @Author:cuijialei
 * @Date: 2019/4/28
 * @Describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DelayedTaskApplication.class})
public class TimeWheelServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeWheelServiceTest.class);

    @Test
    public void TestTimeWheel() {
        TimeWheelService timeWheelService = new TimeWheelServiceImpl();
        LOGGER.info("定时任务当前时间：{} ， 任务执行时间：{}",DateUtils.getStringTime(System.currentTimeMillis()),1+"秒");
        timeWheelService.addTimeTaskNewOrder(1,TimeUnit.SECONDS,"");
        return;
    }
}
