package com.example.designpattern.factory.easy_factory;

import com.example.designpattern.factory.BMW320;
import com.example.designpattern.factory.BMW740;
import com.example.designpattern.factory.Car;

/**
 * @Author:cuijialei
 * @Date: 2019/7/26
 * @Describe
 */
public class SimpleCarFactory {
    public static Car getInstance(String carName){
        switch (carName){
            case "bmw320":
                return new BMW320();
            case "bmw740":
                return new BMW740();
            default:
                throw new RuntimeException("错误的carName");
        }
    }
}
