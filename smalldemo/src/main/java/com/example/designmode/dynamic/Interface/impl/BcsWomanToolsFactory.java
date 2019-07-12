package com.example.designmode.dynamic.Interface.impl;

import com.example.designmode.dynamic.Interface.WomanTools;

/**
 * @Author:cuijialei
 * @Date: 2019/7/12
 * @Describe
 */
public class BcsWomanToolsFactory implements WomanTools {
    @Override
    public String makeWomanTools(int size) {
        System.out.println("BCs公司的"+size +"大小工具(woman)");
        return "BCs公司的"+size +"大小工具(woman)";
    }
}
