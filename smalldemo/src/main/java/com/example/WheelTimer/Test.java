package com.example.WheelTimer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cuijialei
 * @Date: 2018/12/25
 * @Describe
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.test1();
    }

    public void test1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
        System.out.println("start:" + LocalDateTime.now().format(formatter));

        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task1 :"  + LocalDateTime.now().format(formatter));
        }, 5, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task2 :"  + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task3 :"  + LocalDateTime.now().format(formatter));
        }, 5, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task4 :"  + LocalDateTime.now().format(formatter));
        }, 5, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task5 :"  + LocalDateTime.now().format(formatter));
        }, 5, TimeUnit.SECONDS);



    }

    public void test2(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("task1:" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(
                formatter)), 4, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




//    public static void main(String[] argv) {
//
//        MyTimerTask timerTask = new MyTimerTask(true);
//        Timer timer = new HashedWheelTimer();
//        timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);
//
//        int i = 1;
//
//        while (timerTask.flag) {
//
//            try {
//
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//
//                // TODO Auto-generated catch block
//
//                e.printStackTrace();
//
//            }
//
//            System.out.println(i + "秒过去了");
//
//            i++;
//
//        }
//    }
}
