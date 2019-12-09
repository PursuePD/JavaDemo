package com.example.smalldemo.MyThreadPool;

/**
 * @author: 小崔
 * @create: 2019-12-09 15:58
 * @Description: 创建线程的工厂，用于创建线程
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread creatThread(Runnable runnable);
}
