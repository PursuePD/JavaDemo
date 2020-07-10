package com.example.多线程与高并发.join;

/**
 * @author: 小崔
 * @create: 2020-07-10 17:22
 * @Description:
 */
public class ThreadJoinTest2 {
    static class WorkersA implements Runnable {
	@Override
	public void run() {
	    System.out.println("正在运行A");
	}
    }
    static class WorkersB implements Runnable {
	@Override
	public void run() {
	    System.out.println("正在运行B");
	}
    }
    static class WorkersC implements Runnable {
	@Override
	public void run() {
	    System.out.println("正在运行C");
	}
    }

    public static void main(String[] args) throws Exception{
	for (int i = 0; i < 10; i++) {
	    Thread thread1 = new Thread(new WorkersA());
	    thread1.start();
	    thread1.join();
	    Thread thread2 = new Thread(new WorkersB());
	    thread2.start();
	    thread2.join();
	    Thread thread3 = new Thread(new WorkersC());
	    thread3.start();
	    thread3.join();
	}
    }
}
