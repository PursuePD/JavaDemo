package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:42
 * @Description: 适配器
 */
public class SDAdapterTF implements SDCard {

    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
	this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
	System.out.println("adapter read tf card ");
	return tfCard.readTF();
    }

    @Override
    public int writeSD(String msg) {
	System.out.println("adapter write tf card");
	return tfCard.writeTF(msg);
    }
}
