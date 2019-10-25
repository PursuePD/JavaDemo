package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:26
 * @Description:
 */
public class SDCardImpl implements SDCard {

    @Override
    public String readSD() {
	String msg = "sdcard read a msg :hello word SD";
	return msg;
    }

    @Override
    public int writeSD(String msg) {
	System.out.println("sd card write msg : " + msg);
	return 1;
    }
}
