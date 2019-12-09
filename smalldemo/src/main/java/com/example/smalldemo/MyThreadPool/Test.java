package com.example.smalldemo.MyThreadPool;

import com.example.smalldemo.MyThreadPool.impl.BasicThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * @author: 小崔
 * @create: 2019-12-09 16:44
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
	final ThreadPool threadPool =new BasicThreadPool(2,6,4,5);
	for (int i = 0 ; i <= 20 ; i++){
	    threadPool.execute(()->{
		try {
		    TimeUnit.SECONDS.sleep(1);
		    System.out.println(Thread.currentThread().getName()+"开始了");
		    System.out.println(threadPool.getInfo());
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    });
	}

    }

}
