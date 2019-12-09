package com.example.smalldemo.MyThreadPool;

/**
 * @author: 小崔
 * @create: 2019-12-09 15:22
 * @Description:
 */
public interface ThreadPool {

    /**
     * 提交任务到线程池
     * @param runnable
     */
    void execute(Runnable runnable);
    /**
     * 关闭
     */
    void shutdown();
    /**
     * 获取线程池初始化时的线程大小
     * @return
     */
    int getInitSize();
    /**
     * 获取线程池最大线程数
     * @return
     */
    int getMaxSize();
    /**
     * 获取线程池核心线程数量
     * @return
     */
    int getCoreSize();
    /**
     * 获取活跃线程数量
     * @return
     */
    int getActiveCount();
    /**
     * 获取线程池缓存队列大小
     * @return
     */
    int getQueueSize();
    /**
     * 查看线程是否被销毁
     * @return
     */
    boolean isShutdown();

    String getInfo();
}
