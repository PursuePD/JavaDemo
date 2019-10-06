package com.example.设计模式.Strategy策略模式.example2;

/**
 * @author: hz19084340 小崔
 * @create: 2019-10-06 17:31
 * @Description:
 */
public class PaymentContext {

    /**
     * 姓名
     */
    private String userName = null;

    /**
     * 应被支付的工资
     */
    private double money = 0.0;

    /**
     * 支付工资方式的策略接口
     */
    private PaymentStrategy strategy = null;

    public PaymentContext(String userName, double money, PaymentStrategy strategy) {
	this.userName = userName;
	this.money = money;
	this.strategy = strategy;
    }

    public String getUserName() {
	return userName;
    }

    public double getMoney() {
	return money;
    }

    public void payNow(){
        this.strategy.pay(this);
    }
}
