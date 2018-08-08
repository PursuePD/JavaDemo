package com.example.mongodb.service.impl;

import com.example.mongodb.dao.PeopleRepository;
import com.example.mongodb.entity.People;
import com.example.mongodb.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@Service
public class MongoDBServiceImpl implements MongoDBService {

    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public People findBy(String name){
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query,People.class);
    }

    public boolean addPeople(People people){
        peopleRepository.insert(people);
        return true;
    }

    public boolean addPeoples(List<People> peoples){
        mongoTemplate.insert(peoples);
        return true;
    }

}
