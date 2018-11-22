package com.example.mongodb.service.impl;

import com.example.mongodb.dao.PeopleRepository;
import com.example.mongodb.entity.People;
import com.example.mongodb.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@Service("mongoDBServiceImplA")
public class MongoDBServiceImplA implements MongoDBService {

    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public People findBy(String name){
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query,People.class);
    }

    @Override
    public boolean addPeople(People people){
        peopleRepository.insert(people);
        return true;
    }

    @Override
    public boolean addPeoples(List<People> peoples){
        mongoTemplate.insert(peoples,"people");
        return true;
    }

    @Override
    public List<People> findListByAge(int age){
        Query query = new Query(Criteria.where("age").is(age));
        return mongoTemplate.find(query,People.class);
    }

    @Override
    public List<People> findByNameAndAge(String name, int age){
        Query query = new Query(Criteria.where("age").is(age));
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query,People.class);
    }

    @Override
    public void updateAddress(String name, int age, String updateAddress){
        Query query = new Query(Criteria.where("age").is(age));
        query.addCriteria(Criteria.where("name").is(name));
        Update update = Update.update("address",updateAddress);
        mongoTemplate.updateFirst(query,update,People.class);
    }

}
