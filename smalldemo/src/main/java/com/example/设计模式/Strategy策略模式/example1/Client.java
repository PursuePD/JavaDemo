package com.example.设计模式.Strategy策略模式.example1;

/**
 * @author: 小崔
 * @create: 2019-10-06 15:35
 * @Description:
 */
public class Client {

    public static void main(String[] args) {
	//选择创建使用的策略对象
	Strategy strategy = new LargeCustomerStrategy();
	//创建上下文
	Price ctx = new Price(strategy);
	//计算
	double q = ctx.quote(1000);
	System.out.println("报价："+q);
    }
}
