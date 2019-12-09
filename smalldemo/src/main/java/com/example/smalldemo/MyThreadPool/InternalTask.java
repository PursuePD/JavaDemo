package com.example.smalldemo.MyThreadPool;

/**
 * @author: 小崔
 * @create: 2019-12-09 15:50
 * @Description: 现Runnable,用于线程池内部，该类会用到RunnableQueue，会不断的从队列中拿出线程并运行
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
	this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
	//如果当前线程在运行中切没有被中断，则不断从缓存队列中拿出线程运行
	while (running && !Thread.currentThread().isInterrupted()){
	    try {
		Runnable task = runnableQueue.take();
		task.run();
	    } catch (Exception e) {
		running = false;
		break;
	    }
	}
    }

    //停止当前任务，会在shutdown中使用
    public void stop(){
	this.running = false;
    }
}
