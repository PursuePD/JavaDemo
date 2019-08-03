package com.example.设计模式.facade外观模式;

/**
 * @description: 外观模式 基金类
 * @author: CuiJiaLei
 * @create: 2019-08-03 18:31
 **/
public class Fund {
    Stock1 stock1;
    Stock2 stock2;
    Stock3 stock3;
    NationalDebt1 nationalDebt1;
    Realty1 realty1;

    public Fund() {
        //todo: 这里可以用一个容器来装这些实体
        stock1 = new Stock1();
        stock2 = new Stock2();
        stock3 = new Stock3();
        nationalDebt1 = new NationalDebt1();
        realty1 = new Realty1();
    }

    //购买基金
    public void buyFund() {
        //todo: 这里可以根据用户的选择和类型来选择调谁的接口
        stock1.buy();
        stock2.buy();
        stock3.buy();
        nationalDebt1.buy();
        realty1.buy();
    }

    //赎回基金
    public void sellFund() {
        //todo: 这里可以根据用户的选择和类型来选择调谁的接口
        stock1.sell();
        stock2.sell();
        stock3.sell();
        nationalDebt1.sell();
        realty1.sell();
    }
}
