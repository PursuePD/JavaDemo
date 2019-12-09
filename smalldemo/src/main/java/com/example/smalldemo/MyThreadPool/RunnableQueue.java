package com.example.smalldemo.MyThreadPool;

/**
 * @author: 小崔
 * @create: 2019-12-09 15:30
 * @Description:
 */
public interface RunnableQueue {

    /**
     * 新线程进来时,提交任务到缓存队列
     * @param runnable
     */
    void offer(Runnable runnable);
    /**
     * 取出线程
     * @return
     */
    Runnable take();

    /**
     * 获取队列中线程的数量
     * @return
     */
    int size();
}
