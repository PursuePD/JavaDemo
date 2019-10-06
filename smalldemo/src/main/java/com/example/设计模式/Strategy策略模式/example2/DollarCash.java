package com.example.设计模式.Strategy策略模式.example2;

/**
 * @author: hz19084340 小崔
 * @create: 2019-10-06 17:56
 * @Description:
 */
public class DollarCash implements PaymentStrategy {

    @Override
    public void pay(PaymentContext ctx) {
	System.out.println("现在给"+ctx.getUserName()+"美元现金支付"+ctx.getMoney()+"元");
    }
}
