package com.example.设计模式.Adapter适配器模式.example;

/**
 * @author: 小崔
 * @create: 2019-10-25 15:43
 * @Description:
 */
public class Client {

    public static void main(String[] args) {
	Computer computer = new ComputerImpl();
	SDCard sdCard = new SDCardImpl();
	System.out.println(computer.readSD(sdCard));
	System.out.println("====================================");
	TFCard tfCard = new TFCardImpl();
	SDCard tfCardAdapterSD = new SDAdapterTF(tfCard);
	System.out.println(computer.readSD(tfCardAdapterSD));
    }
}
