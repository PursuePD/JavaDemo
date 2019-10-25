package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:27
 * @Description:
 */
public class ComputerImpl implements Computer {

    @Override
    public String readSD(SDCard sdCard) {
	if(sdCard == null){
	    throw new NullPointerException("sd card null");
	}
	return sdCard.readSD();
    }
}
