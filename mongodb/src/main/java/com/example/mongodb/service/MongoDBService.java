package com.example.mongodb.service;

import com.example.mongodb.entity.People;

import java.util.List;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
public interface MongoDBService {

    People findBy(String name);

    boolean addPeople(People people);

    boolean addPeoples(List<People> peoples);
}
