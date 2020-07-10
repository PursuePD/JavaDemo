package com.example.多线程与高并发.join;

/**
 * @author: 小崔
 * @create: 2020-07-10 17:28
 * @Description:
 */
public class ThreadJoinTest3 {
    static class WorkersA implements Runnable {
	@Override
	public void run() {
	    System.out.println("正在运行A");
	}
    }
    static class WorkersB implements Runnable {
	@Override
	public void run() {
	    Thread threadA = new Thread(new WorkersA());
	    threadA.start();
	    try {
		threadA.join();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println("正在运行B");
	}
    }
    static class WorkersC implements Runnable {
	@Override
	public void run() {
	    Thread threadB = new Thread(new WorkersB());
	    threadB.start();
	    try {
		threadB.join();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println("正在运行C");
	}
    }

    public static void main(String[] args) throws Exception{
	Thread threadC = new Thread(new WorkersC());
	threadC.start();
    }
}
