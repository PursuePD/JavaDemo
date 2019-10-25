package com.example.设计模式.Strategy策略模式.example1;

/**
 * @author: 小崔
 * @create: 2019-10-06 15:19
 * @Description:
 */
public class OldCustomerStrategy implements Strategy {

    @Override
    public double calcPrice(double goodsPrice) {
	System.out.println("老客户统一折扣5%");
	return goodsPrice*(1-0.05);
    }
}
