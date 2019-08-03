package com.example.设计模式.facade外观模式;

/**
 * @description: 测试类
 * @author: CuiJiaLei
 * @create: 2019-08-03 18:35
 **/
public class FundTest {
    public static void main(String[] args) {
        Fund fund = new Fund();

        //基金购买
        fund.buyFund();
        System.out.println("-------------");
        //基金赎回
        fund.sellFund();
    }
}
