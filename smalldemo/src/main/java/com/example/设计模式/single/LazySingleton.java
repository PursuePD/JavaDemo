package com.example.设计模式.single;

/**
 * @description: 懒汉式单例模式 实例化的时机挪到了 getInstance() 方法中，做到了 lazy init ，
 *                 但也失去了类加载时期初始化的线程安全保障。
 *                 因此使用了 synchronized 关键字来保障线程安全。
 * @author: CuiJiaLei
 * @create: 2019-07-24 00:25
 **/
public class LazySingleton {
    private static LazySingleton mInstance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (mInstance == null){
            mInstance = new LazySingleton();
        }
        return mInstance;
    }
}
