package com.example.设计模式.Strategy策略模式.example2;

/**
 * @author: hz19084340 小崔
 * @create: 2019-10-06 18:02
 * @Description:
 */
public class BankCardPay implements PaymentStrategy {

    @Override
    public void pay(PaymentContext ctx) {
	//新算法要使用扩展的上下文，需要强制转换
	PaymentContextByBank paymentContextByBank = (PaymentContextByBank)ctx;
	System.out.println("现在给"+paymentContextByBank.getUserName()+"账户"
		+paymentContextByBank.getAcount()+"打款支付"+paymentContextByBank.getMoney()+"元");
    }
}
