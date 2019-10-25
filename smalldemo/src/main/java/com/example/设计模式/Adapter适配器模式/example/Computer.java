package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:27
 * @Description:
 */
public interface Computer {

    /**
     * 电脑读取sd卡
     * @param sdCard
     * @return
     */
    public String readSD(SDCard sdCard);
}
