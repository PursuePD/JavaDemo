package com.example.设计模式.Strategy策略模式.example2;

/**
 * @author: 小崔
 * @create: 2019-10-06 17:28
 * @Description: 支付工资接口，多种工资类型RMB,Dollar，多种支付方式（现金，银行卡）
 */
public interface PaymentStrategy {

    /**
     * 发工资
     * @param ctx 支付工资的上下文
     */
    public void pay(PaymentContext ctx);
}
