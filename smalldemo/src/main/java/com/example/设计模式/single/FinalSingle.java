package com.example.设计模式.single;

import java.io.Serializable;

/**
 * @description: 进化成了完全体，延迟加载，线程安全，反射安全，序列化安全
 * @author: CuiJiaLei
 * @create: 2019-07-24 00:37
 **/
public class FinalSingle implements Serializable {
    //volatile 防止指令重排

    private  static FinalSingle mInstance;

    private FinalSingle() {
        if (mInstance!=null){
            throw new RuntimeException("想反射我，没门！");
        }
    }

    public static FinalSingle getInstance() {
        if (mInstance == null) {
            synchronized (FinalSingle.class) {
                if (mInstance == null){
                    mInstance = new FinalSingle();
                }
            }
        }
        return mInstance;
    }

    protected Object readResolve() {
        return getInstance();
    }
}
