package com.example.smalldemo.Thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cuijialei
 * @Date: 2019/7/23
 * @Describe
 */
public class ScheduleWorkerTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.HasException),1,3,TimeUnit.SECONDS);
        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.Normal),1,3,TimeUnit.SECONDS);
    }
}
