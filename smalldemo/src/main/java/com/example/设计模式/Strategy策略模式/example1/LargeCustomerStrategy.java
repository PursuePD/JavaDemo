package com.example.设计模式.Strategy策略模式.example1;

/**
 * @author: 小崔
 * @create: 2019-10-06 15:20
 * @Description:
 */
public class LargeCustomerStrategy implements Strategy {

    @Override
    public double calcPrice(double goodsPrice) {
	System.out.println("大客户，统一折扣10%");
	return goodsPrice*(1-0.1);
    }
}
