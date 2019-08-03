package com.example.设计模式.facade外观模式;

/**
 * @description: 股票1
 * @author: CuiJiaLei
 * @create: 2019-08-03 18:32
 **/
public class Stock1 implements FundInf  {
    //买股票
    @Override
    public void buy() {
        System.out.println("股票1买入");
    }

    //卖股票
    @Override
    public void sell() {
        System.out.println("股票1卖出");
    }
}
