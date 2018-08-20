package com.example.smalldemo.Thread;

/**
 * @Author:cuijialei
 * @Date: 2018/8/20
 * @Describe 另起线程实现异步操作
 */
public class AsynchronizationThread {
    public static void main(String[] args){
        System.out.println("开始做蛋糕");
        // (2) 为了建立RealData的实体，启动新的线程

        new Thread(){
            public void run() {
                //在匿名内部类中使用count、future、c。
                for (int i=1;i<=5;i++){
                    try {
                        Thread.sleep(1000);
                        System.out.println(i+"小时后");
                    } catch (InterruptedException e) {
                    }
                }

                System.out.println("做完蛋糕,通知客户过来拿吧");
            }
        }.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                }
//                System.out.println("做完蛋糕,通知客户过来拿吧");
//            }
//        }).start();
//
        System.out.println("你先回去等吧");

    }
}
