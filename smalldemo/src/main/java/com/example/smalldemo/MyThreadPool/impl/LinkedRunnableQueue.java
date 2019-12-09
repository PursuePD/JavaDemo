package com.example.smalldemo.MyThreadPool.impl;

import com.example.smalldemo.MyThreadPool.RunnableQueue;
import com.example.smalldemo.MyThreadPool.ThreadPool;
import com.example.smalldemo.MyThreadPool.deny.DenyPolicy;

import java.util.LinkedList;

/**
 * @author: 小崔
 * @create: 2019-12-09 15:37
 * @Description:
 */
public class LinkedRunnableQueue implements RunnableQueue {
    //队列最大容量
    private final int limit;
    //拒绝策略
    private final DenyPolicy denyPolicy;
    //存放任务的队列
    private final LinkedList<Runnable> runnableLinkedList;
    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
	this.limit = limit;
	this.denyPolicy = denyPolicy;
	this.threadPool = threadPool;
	runnableLinkedList = new LinkedList<>();
    }

    @Override
    public void offer(Runnable runnable) {
	synchronized (runnableLinkedList) {
	    //如果缓存数量超过最大值，则使用拒绝策略
	    if (runnableLinkedList.size() >= limit) {
		denyPolicy.reject(runnable, threadPool);
	    } else {
		//成功加入list的末尾，并唤醒阻塞中的线程
		runnableLinkedList.addLast(runnable);
		runnableLinkedList.notifyAll();
	    }
	}
    }

    @Override
    public Runnable take() {
	synchronized (runnableLinkedList) {
	    //如果缓存队列为空，则挂起，等待新的任务进来唤醒
	    while (runnableLinkedList.isEmpty()) {
		try {
		    runnableLinkedList.wait();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
	return runnableLinkedList.removeFirst();
    }

    @Override
    public int size() {
	synchronized (runnableLinkedList){
	    //返回list中的个数
	    return runnableLinkedList.size();
	}
    }
}
