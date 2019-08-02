package com.example.设计模式.single;

/**
 * 饿汉式
 */
public class HungrySingleton {
    private static final HungrySingleton mInstance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return mInstance;
    }

}
