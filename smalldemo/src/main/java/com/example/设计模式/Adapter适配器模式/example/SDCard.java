package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:23
 * @Description: 读卡器
 */
public interface SDCard {
    /**
     * 读取SD卡方法
     * @return
     */
    public String readSD();
    /**
     * 写入SD卡功能
     * @param msg
     * @return
     */
    public int writeSD(String msg);
}
