package com.example.设计模式.facade外观模式;

/**
 * @description: 股票2
 * @author: CuiJiaLei
 * @create: 2019-08-03 18:32
 **/
public class Stock2 implements FundInf  {

    //买股票
    @Override
    public void buy() {
        System.out.println("股票2买入");
    }

    //卖股票
    @Override
    public void sell() {
        System.out.println("股票2卖出");
    }
}
