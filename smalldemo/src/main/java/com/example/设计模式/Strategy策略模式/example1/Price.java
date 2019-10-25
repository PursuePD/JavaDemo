package com.example.设计模式.Strategy策略模式.example1;

/**
 * @author: 小崔
 * @create: 2019-10-06 15:30
 * @Description: 价格管理，主要完成计算向客户所报价功能
 */
public class Price {

    /**
     * 具有一个具体的策略对象
     */
    private Strategy strategy = null;


    public Price(Strategy strategy) {
	this.strategy = strategy;
    }

    /**
     * 报价，计算客户对应的报价
     * @param goodsPrice
     * @return
     */
    public double quote(double goodsPrice){
        return this.strategy.calcPrice(goodsPrice);
    }
}
