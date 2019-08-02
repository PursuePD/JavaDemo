package com.example.设计模式.factory.easy_factory;

import com.example.设计模式.factory.Car;

/**
 * @Author:cuijialei
 * @Date: 2019/7/26
 * @Describe
 */
public class SimpleCarFactoryTest {
    public static void main(String[] args) {
        Car car = SimpleCarFactory.getInstance("bmw320");
        System.out.println(car.getCarName());
    }
}
