package com.example.多线程与高并发.yiled;

/**
 * @author: 小崔 hz19084340
 * @create: 2020-08-19 17:45
 * @Description:
 */
public class YiledTest {


    public static void main(String[] args) {
	Runnable runnable = () -> {
	    for (int i = 0; i <= 100; i++) {
		System.out.println(Thread.currentThread().getName() + "-----" + i);
		if (i % 20 == 0) {
		    Thread.yield();
		}
	    }
	};
	new Thread(runnable, "栈长").start();
	new Thread(runnable, "小蜜").start();
    }

}
