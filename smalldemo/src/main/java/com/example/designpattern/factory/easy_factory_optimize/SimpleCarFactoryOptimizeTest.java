package com.example.designpattern.factory.easy_factory_optimize;

import com.example.designpattern.factory.Car;

/**
 * @Author:cuijialei
 * @Date: 2019/7/26
 * @Describe
 */
public class SimpleCarFactoryOptimizeTest {
    public static void main(String[] args) {
        Car car = SimpleCarFactoryOptimize.getInstance("bmw530");
        System.out.println(car.getCarName());
    }
}
