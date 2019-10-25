package com.example.设计模式.Strategy策略模式.example2;

/**
 * @author: 小崔
 * @create: 2019-10-06 18:08
 * @Description:
 */
public class BankCardPay2 implements PaymentStrategy {

    private String account = null;

    public BankCardPay2(String account) {
	this.account = account;
    }

    @Override
    public void pay(PaymentContext ctx) {
	System.out.println("现在给"+ctx.getUserName()+"账户"
		+this.account+"打款支付"+ctx.getMoney()+"元");
    }
}
