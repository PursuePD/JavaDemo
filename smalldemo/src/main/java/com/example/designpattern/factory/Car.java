package com.example.designpattern.factory;

/**
 * @Author:cuijialei
 * @Date: 2019/7/26
 * @Describe
 */
public abstract class Car {

    protected String carName;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
