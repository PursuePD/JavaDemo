package com.example.设计模式.Strategy策略模式.example1;

/**
 * @author: 小崔
 * @create: 2019-10-06 15:17
 * @Description:
 */
public class NormalCustomerStrategy implements Strategy {

    @Override
    public double calcPrice(double goodsPrice) {
	System.out.println("新客户或普通客户，无折扣");
	return goodsPrice;
    }
}
