package com.example.mongodb.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
public class People {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private int age;
    @ApiModelProperty(value = "地址")
    private String address;

    public People(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
