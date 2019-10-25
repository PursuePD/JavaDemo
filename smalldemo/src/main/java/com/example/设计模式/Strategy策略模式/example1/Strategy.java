package com.example.设计模式.Strategy策略模式.example1;

/**
 * @author: 小崔
 * @create: 2019-10-06 15:15
 * @Description:
 */
public interface Strategy {

    /**
     * 计算应报的价格
     * @param goodsPrice 商品原价格
     * @return
     */
    public double calcPrice(double goodsPrice);
}
