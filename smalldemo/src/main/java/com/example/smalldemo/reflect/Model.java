package com.example.smalldemo.reflect;

/**
 * @Author:cuijialei
 * @Date: 2019/6/24
 * @Describe
 */
public class Model {

    private String name;
    private int age;

    public Model(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Model() {
    }

    public String sout(String b){
        System.out.println(name + " " +age+" b:"+ b);
        return name + " " +age+" b:"+ b;
    }

    private String privateSout(String a){
        System.out.println("private" + name + " " +age +" a:"+ a);
        return "private" + name + " " +age +" a:"+ a;
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
}
