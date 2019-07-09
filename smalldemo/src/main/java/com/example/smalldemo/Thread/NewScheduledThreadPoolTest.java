package com.example.smalldemo.Thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author:cuijialei
 * @Date: 2019/7/9
 * @Describe 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
 */
public class NewScheduledThreadPoolTest {

    public static void main(String[] args) {
        runNewScheduledThreadPoolTest();
    }

    public static void runNewScheduledThreadPoolTest(){
        //ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(3);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(3,namedThreadFactory);

        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延时3秒");
            }
        },3,TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟 1 秒后每三秒执行一次");
            }
        },1,3,TimeUnit.SECONDS);

    }

}
