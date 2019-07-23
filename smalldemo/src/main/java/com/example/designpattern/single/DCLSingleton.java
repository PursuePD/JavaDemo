package com.example.designpattern.single;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @description: 单例模式：双重检查锁定(DCL, Double Check Lock)
 *   考虑反射安全和序列化安全
 * @author: CuiJiaLei
 * @create: 2019-07-24 00:27
 **/
public class DCLSingleton {
    private static DCLSingleton mInstance;

//    private DCLSingleton() {
//    }

    /**
     * 防止反射破坏单例模式
     */
    private DCLSingleton() {
        if (mInstance!=null){
            throw new RuntimeException("想反射我，没门！");
        }
    }

    /**
     * 1 处做第一次判断，如果已经实例化了，直接返回对象，避免无用的同步消耗。
     * 2 处仅对实例化过程做同步操作，保证单例。
     * 3 处做第二次判断，只有 mInstance 为空时再初始化。看起来时多么的完美，保证线程安全的同时又兼顾性能。
     * 但是 DCL 存在一个致命缺陷，就是重排序导致的多线程访问可能获得一个未初始化的对象。
     *
     * 其中第 4 行代码 mInstance = new DCLSingleton(); 在 JVM 看来有这么几步：
         * 为对象分配内存空间
         * 初始化对象
         * 将 mInstance 引用指向第 1 步中分配的内存地址
     * @return
     */
    public static DCLSingleton getInstance() {
        if (mInstance == null) {                    // 1
            synchronized (DCLSingleton.class) {     // 2
                if (mInstance == null)  {           // 3
                    mInstance = new DCLSingleton(); // 4
                }
            }
        }
        return mInstance;
    }


    public static void main(String[] args) {
        //通过反射破坏了单例
        //blockSingle();
        //通过序列化破坏了单例
        blockSingle1();

    }

    /**
     * 通过反射破坏了单例
     * @param
     */
    public static void blockSingle(){
        DCLSingleton singleton1 = DCLSingleton.getInstance();
        DCLSingleton singleton2 = null;

        try {
            Class<DCLSingleton> clazz = DCLSingleton.class;
            Constructor<DCLSingleton> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            singleton2 = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(singleton1.hashCode());
        System.out.println(singleton2.hashCode());
    }

    /**
     * 通过序列化破坏了单例
     */
    public static void blockSingle1(){
        DCLSingleton singleton1 = DCLSingleton.getInstance();
        DCLSingleton singleton2 = null;

        try {
            ObjectOutput output=new ObjectOutputStream(new FileOutputStream("singleton.ser"));
            output.writeObject(singleton1);
            output.close();

            ObjectInput input=new ObjectInputStream(new FileInputStream("singleton.ser"));
            singleton2= (DCLSingleton) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(singleton1.hashCode());
        System.out.println(singleton2.hashCode());

    }
}
