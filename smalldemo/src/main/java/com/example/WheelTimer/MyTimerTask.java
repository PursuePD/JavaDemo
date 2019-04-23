package com.example.WheelTimer;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;

/**
 * @Author:cuijialei
 * @Date: 2019/1/7
 * @Describe
 */
public class MyTimerTask  implements TimerTask {
    boolean flag;

    public MyTimerTask(boolean flag){

        this.flag = flag;

    }

    @Override
    public void run(Timeout timeout) throws Exception {

        // TODO Auto-generated method stub

        System.out.println("要去数据库删除订单了。。。。");

        this.flag =false;

    }
}
