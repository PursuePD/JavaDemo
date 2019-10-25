package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:40
 * @Description:
 */
public interface TFCard {
    /**
     * 读取TF卡方法
     * @return
     */
    public String readTF();
    /**
     * 写入TF卡功能
     * @param msg
     * @return
     */
    public int writeTF(String msg);
}
