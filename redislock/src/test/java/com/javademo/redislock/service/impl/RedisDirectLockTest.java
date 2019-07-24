package com.javademo.redislock.service.impl;

import com.javademo.redislock.service.MyDistributedLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.javademo.redislock.LockApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @Author:cuijialei
 * @Date: 2019/7/24
 * @Describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LockApp.class)
public class RedisDirectLockTest {
    public static int i = 0;

    @Autowired
    private MyDistributedLock myDistributedLock;

    private static final String addNumLockName = "addNumLockName";
    private static final int RETRY_TIMES = 5;
    private static final long RETRY_TIME_OUT = 2000;

    @Test
    public void redisLockTest(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for(int i = 0;i<3;i++ ){
            pool.execute(new addOne());
        }
    }

    private class addOne implements Runnable{
        @Override
        public void run() {
            String txId = Thread.currentThread().getName();
            boolean success = myDistributedLock.lock(addNumLockName,txId);
            for (int i = 0; i < RETRY_TIMES && !success; i++) {
                success = retry(myDistributedLock, txId, i + 1);
            }
            if(!success){
                System.out.println("线程：" + txId + "获取锁失败");
                return;
            }
            System.out.println("-----------线程：" + txId + "上锁成功--------");
            System.out.println("线程：" + txId + "开始执行事务");
            i++;
            System.out.println(Thread.currentThread().getName() + "  i 的大小:"+i);
            System.out.println("线程：" + txId + "任务执行完毕，开始释放获取到的锁");
            boolean unlock = myDistributedLock.unlock(addNumLockName, txId);
            if (unlock) {
                System.out.println("线程：" + txId + "锁释放成功");
                return;
            }
            System.out.println("线程：" + txId + "锁释放失败，事务回滚");
        }
    }

    private boolean retry(MyDistributedLock lock, String txId, int i) {
        System.out.println("线程：" + Thread.currentThread().getName() + "正在尝试第" +
                i + "次重新获取锁");
        sleepSeconds(RETRY_TIME_OUT);
        return lock.lock(addNumLockName, txId);
    }

    private void sleepSeconds(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}