package com.example.springtest.service.impl;

import com.example.springtest.DelayedTaskApplication;

import com.example.springtest.service.RedisDelayedTaskService;
import com.example.springtest.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;


/**
 * @Author:cuijialei
 * @Date: 2019/4/29
 * @Describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DelayedTaskApplication.class})
public class RedisDelayedTaskServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDelayedTaskServiceImplTest.class);

    @Autowired
    RedisDelayedTaskService redisDelayedTaskService;

    @Test
    public void addTask() {
        for(int i= 10 ; i<= 16 ; i=i+1){
            long time = System.currentTimeMillis() + i*1000;
            redisDelayedTaskService.addTask(DateUtils.getLongTime(time),"订单号:"+i+"应该执行任务时间:"+DateUtils.getStringTime(time));
        }

    }

    @Test
    public void addTask1() {
        for(int i= 10 ; i<= 15 ; i=i+1){
            long time = System.currentTimeMillis() + i*1000;
            for(int j = 0;j<5;j++){
                redisDelayedTaskService.addTask(DateUtils.getLongTime(time),"订单号:"+i+j+",应该执行任务时间:"+DateUtils.getStringTime(time));
            }
        }

    }

    @Test
    public void getFirstTask() {
        redisDelayedTaskService.getFirstReadyTaskAndRemove();
    }

    @Test
    public void getRangeTask(){
        long endTime = DateUtils.getLongTime(System.currentTimeMillis()) ;
        Collection<String> collection = redisDelayedTaskService.getToDoRangeTask(endTime);
        LOGGER.info(collection.toString());
    }



}
