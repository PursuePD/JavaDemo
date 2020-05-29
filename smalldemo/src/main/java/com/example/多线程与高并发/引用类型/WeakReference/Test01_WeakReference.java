package com.example.多线程与高并发.引用类型.WeakReference;

import com.example.多线程与高并发.引用类型.M;

import java.lang.ref.WeakReference;

/**
 * @author: 小崔
 * @create: 2020-05-29 15:43
 * @Description:
 */
public class Test01_WeakReference {

    public static void main(String[] args) {
	WeakReference<M> m = new WeakReference<>(new M());
	//M strong = m.get();//加入这个会让弱引用变成强引用
	System.out.println(m.get());
	System.gc();
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println(m.get());
    }
}
