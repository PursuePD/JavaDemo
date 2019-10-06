package com.example.设计模式.Strategy策略模式.example2;

/**
 * @author: hz19084340 小崔
 * @create: 2019-10-06 17:56
 * @Description:
 */
public class Client {

    public static void main(String[] args) {
	PaymentStrategy rmbStrategy = new RMBCash();
	PaymentStrategy dollarStrategy = new DollarCash();

	PaymentContext ctx1 = new PaymentContext("小李",5000,rmbStrategy);
	ctx1.payNow();
	PaymentContext ctx2 = new PaymentContext("cat",700,dollarStrategy);
	ctx2.payNow();

	//扩展银行打款
	//扩展方式1
	PaymentStrategy bankCardStrategy = new BankCardPay();
	PaymentContext ctx3 = new PaymentContextByBank("peter",700,bankCardStrategy,"5100615345332");
	ctx3.payNow();
	//扩展方式2
	PaymentStrategy backCard2Strategy = new BankCardPay2("51313541123132");
	PaymentContext ctx4 = new PaymentContext("tom",1200,backCard2Strategy);
	ctx4.payNow();
    }
}
