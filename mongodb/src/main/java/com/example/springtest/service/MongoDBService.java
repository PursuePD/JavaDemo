package com.example.springtest.service;

import com.example.springtest.entity.People;

import java.util.List;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
public interface MongoDBService {
    /**
     * 通过name 查询一个people
     * @param name
     * @return
     */
    People findBy(String name);

    /**
     * 添加一个people
     * @param people
     * @return
     */
    boolean addPeople(People people);

    /**
     * 添加多个people
     * @param peoples
     * @return
     */
    boolean addPeoples(List<People> peoples);

    /**
     * 通过age查询所有的people
     * @param age
     * @return
     */
    List<People> findListByAge(int age);

    /**
     * 通过age和name查询所有的people
     * @param name
     * @param age
     * @return
     */
    List<People> findByNameAndAge(String name,int age);

    /**
     * 通过name 和age  修改第一个的address
     * @param name
     * @param age
     * @param updateAddress
     */
    void updateAddress(String name,int age,String updateAddress);

    String test();
}
