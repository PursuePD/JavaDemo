package com.example.java8.Stream.model;

/**
 * @Author:cuijialei
 * @Date: 2019/4/23
 * @Describe
 */
public class PersonModelCopy {
    private String name;
    private int age;
    private boolean man;
    private double height;

    public PersonModelCopy() {
    }

    public PersonModelCopy(String name, int age, boolean man, double height) {
        this.name = name;
        this.age = age;
        this.man = man;
        this.height = height;
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

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", man=" + man +
                ", height=" + height +
                '}';
    }
}
