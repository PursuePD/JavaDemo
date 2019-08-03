package com.example.设计模式.facade外观模式;

/**
 * @description: 房地产
 * @author: CuiJiaLei
 * @create: 2019-08-03 18:34
 **/
public class Realty1 implements FundInf  {
    //买房地产
    @Override
    public void buy() {
        System.out.println("房地产买入");
    }

    //卖房地产
    @Override
    public void sell() {
        System.out.println("房地产卖出");
    }
}
