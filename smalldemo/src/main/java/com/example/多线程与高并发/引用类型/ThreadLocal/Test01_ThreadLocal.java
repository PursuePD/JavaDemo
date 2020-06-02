package com.example.多线程与高并发.引用类型.ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @author: 小崔
 * @create: 2020-06-02 11:45
 * @Description:
 */
public class Test01_ThreadLocal {

    static  ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(()->{
	    try {
		TimeUnit.SECONDS.sleep(2);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println(tl.get()); // null 拿不到
	}).start();

	new Thread(()->{
	    try {
		TimeUnit.SECONDS.sleep(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    tl.set(new Person());
	}).start();
    }

    static class Person{
        String name = "zhangsan";
    }
}
