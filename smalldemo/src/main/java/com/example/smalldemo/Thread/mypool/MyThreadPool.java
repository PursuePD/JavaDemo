package com.example.smalldemo.Thread.mypool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author:cuijialei
 * @Date: 2019/7/17
 * @Describe
 */
public class MyThreadPool {

    //线程池中默认线程的个数为5
    private static int WORK_NUM = 5;
    //队列默认任务个数为100
    private static int TASK_COUNT = 100;

    //工作线程组
    private WorkThread[] workThreads;

    //任务队列
    private final BlockingQueue<Runnable>  taskQueue;
    private final int workerNum; //用户在这个池，希望的启动的线程数

    public MyThreadPool(){
        this(WORK_NUM,TASK_COUNT);
    }
    //创建线程池，workerNum 为线程池中工作线程个数
    public MyThreadPool(int workerNum, int taskCount){
        if(workerNum <= 0 ){
            workerNum = WORK_NUM;
        }
        if(taskCount <= 0 ){
            taskCount = TASK_COUNT;
        }
        this.workerNum = workerNum;
        taskQueue = new ArrayBlockingQueue<>(taskCount);
        workThreads = new WorkThread[workerNum];
        for (int i = 0 ; i<workerNum;i++){
            workThreads[i] = new WorkThread();
            workThreads[i].run();
        }
    }

    //执行任务，其实只是把任务加入到任务队列，什么时候执行有线程池管理器决定
    public void execute(Runnable task){
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //销毁线程池，该方法保证所有任务都完成的情况下才销毁所有的线程，否则等待任务完成才销毁
    public void destory(){
        System.out.println("ready close pool ....");
        for (WorkThread workThread : workThreads) {
            workThread.stopWorker();
            workThread = null ;//help gc
        }
        taskQueue.clear();
    }

    /**
     * 内部类
     */
    private class WorkThread extends Thread{

        @Override
        public void run() {
            Runnable runnable = null;
            try {
                while(!isInterrupted()) {
                    runnable = taskQueue.take();
                    if (runnable != null) {
                        System.out.println(getId() + " reade exec " + runnable);
                        runnable.run();
                    }
                    runnable = null; //help gc
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void stopWorker(){
            interrupt();
        }
    }
}
