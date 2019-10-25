package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:41
 * @Description:
 */
public class TFCardImpl implements TFCard{

    @Override
    public String readTF() {
	String msg ="tf card reade msg : hello word tf card";
	return msg;
    }

    @Override
    public int writeTF(String msg) {
	System.out.println("tf card write a msg : " + msg);
	return 1;
    }
}
