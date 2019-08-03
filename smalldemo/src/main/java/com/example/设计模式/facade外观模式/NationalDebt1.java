package com.example.设计模式.facade外观模式;

/**
 * @description: 国债
 * @author: CuiJiaLei
 * @create: 2019-08-03 18:34
 **/
public class NationalDebt1 implements FundInf {
    //买国债
    @Override
    public void buy() {
        System.out.println("国债买入");
    }

    //卖国债
    @Override
    public void sell() {
        System.out.println("国债卖出");
    }
}
