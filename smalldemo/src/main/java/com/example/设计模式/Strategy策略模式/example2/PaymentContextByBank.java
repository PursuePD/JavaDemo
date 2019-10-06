package com.example.设计模式.Strategy策略模式.example2;

/**
 * @author: hz19084340 小崔
 * @create: 2019-10-06 18:00
 * @Description:
 */
public class PaymentContextByBank extends PaymentContext {

    /**
     * 银行账户
     */
    private String acount = null;

    public PaymentContextByBank(String userName, double money, PaymentStrategy strategy, String acount) {
	super(userName, money, strategy);
	this.acount = acount;
    }

    public String getAcount() {
	return acount;
    }


}
