package com.example.designmode.dynamic.Interface.impl;

import com.example.designmode.dynamic.Interface.ManTools;

/**
 * @Author:cuijialei
 * @Date: 2019/7/12
 * @Describe
 */
public class AsManToolsFactory implements ManTools {
    @Override
    public String makeManTools(int size) {
        System.out.println("As公司的"+size +"大小工具(man)");
        return "As公司的"+size +"大小工具(man)";
    }
}
