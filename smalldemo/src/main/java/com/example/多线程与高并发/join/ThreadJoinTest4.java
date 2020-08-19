package com.example.多线程与高并发.join;

/**
 * @author: 小崔
 * @create: 2020-07-10 17:28
 * @Description:
 */
public class ThreadJoinTest4 {

    public static void main(String[] args) {
	testJoin();
    }

    static void testJoin() {
	Thread t1 = new Thread(()->{
	    for(int i=0; i<10; i++) {
		System.out.println("A" + i);
		try {
		    Thread.sleep(500);
		    //TimeUnit.Milliseconds.sleep(500)
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});

	Thread t2 = new Thread(()->{

	    try {
		t1.join();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    for(int i=0; i<10; i++) {
		System.out.println("B" + i);
		try {
		    Thread.sleep(500);
		    //TimeUnit.Milliseconds.sleep(500)
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});

	t1.start();
	t2.start();
    }
}
