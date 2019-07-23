package com.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:cuijialei
 * @Date: 2019/7/16
 * @Describe
 */
public class ReentrantLockTest {
    private Lock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();//创建 Condition

    public void testMethod() {
        try {
            lock.lock();//lock 加锁
            //1：wait 方法等待：
            //System.out.println("开始 wait");
            condition.await();
            //通过创建 Condition 对象来使线程 wait，必须先执行 lock.lock 方法获得锁
            //:2：signal 方法唤醒
            condition.signal();//condition 对象的 signal 方法可以唤醒 wait 线程
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()+ (" " + (i + 1)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }


    public void test1() {
        try {
//            lock.lock();//lock 加锁
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()+ (" " + (i + 1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
//            lock.unlock();
        }
    }
    public void test2() {
        try {
            lock.lock();//lock 加锁
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()+ (" " + (i + 1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();

        /**
         * Lock的简单测试
         */
//        new Thread(reentrantLockTest::test2).start();
//        new Thread(reentrantLockTest::test2).start();
//        new Thread(reentrantLockTest::test2).start();
//        new Thread(reentrantLockTest::test2).start();
//        new Thread(reentrantLockTest::test2).start();


        new Thread(reentrantLockTest::test1).start();
        new Thread(reentrantLockTest::test1).start();
        new Thread(reentrantLockTest::test1).start();
        new Thread(reentrantLockTest::test1).start();
        new Thread(reentrantLockTest::test1).start();
        /**
         * -------------------------------------------------------------------
         */


    }
}

