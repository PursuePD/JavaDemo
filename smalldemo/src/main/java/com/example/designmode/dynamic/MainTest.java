package com.example.designmode.dynamic;

import com.example.designmode.dynamic.Interface.ManTools;
import com.example.designmode.dynamic.Interface.ProxyCompany;
import com.example.designmode.dynamic.Interface.WomanTools;
import com.example.designmode.dynamic.Interface.impl.AsManToolsFactory;
import com.example.designmode.dynamic.Interface.impl.BcsWomanToolsFactory;

/**
 * @Author:cuijialei
 * @Date: 2019/7/12
 * @Describe
 */
public class MainTest {
    public static void main(String[] args) {

        AsManToolsFactory asManToolsFactory = new AsManToolsFactory();
        BcsWomanToolsFactory bcsWomanToolsFactory = new BcsWomanToolsFactory();

        ProxyCompany proxyCompany = new ProxyCompany();

        proxyCompany.setFactory(asManToolsFactory);
        //
        ManTools manTools = (ManTools)proxyCompany.getProxyInstance();
        manTools.makeManTools(36);

        System.out.println("--------------------");

        proxyCompany.setFactory(bcsWomanToolsFactory);
        WomanTools womanTools = (WomanTools)proxyCompany.getProxyInstance();
        womanTools.makeWomanTools(18);

    }
}
