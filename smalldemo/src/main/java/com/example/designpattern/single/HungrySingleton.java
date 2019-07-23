package com.example.designpattern.single;

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
