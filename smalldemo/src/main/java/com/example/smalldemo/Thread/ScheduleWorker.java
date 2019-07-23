package com.example.smalldemo.Thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:cuijialei
 * @Date: 2019/7/23
 * @Describe 定时任务工作类
 */
public class ScheduleWorker implements  Runnable {

    public final static int Normal = 0 ; //普通任务
    public final static int HasException = -1 ;  //触发异常的任务
    public final static int processException = 1 ; //触发异常但是会被捕捉的任务

    public static SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private int taskType;

    public ScheduleWorker(int taskType) {
        this.taskType = taskType;
    }

    @Override
    public void run() {
        if(taskType == HasException){
            System.out.println(format.format(new Date()) +" Exception made...");
            throw new RuntimeException();
        }else if(taskType == processException){
            try {
                System.out.println(format.format(new Date()) +" Exception made ,but catch");
            }catch (Exception e){
                System.out.println("Exception catched");
            }
        }else{
            System.out.println(format.format(new Date())+" Normal...");
        }

    }
}
