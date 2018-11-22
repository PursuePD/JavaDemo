package com.example.springtest.service.impl;

import com.example.springtest.dao.PeopleRepository;
import com.example.springtest.entity.People;
import com.example.springtest.service.MongoDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service("mongoDBServiceImpl")
public class MongoDBServiceImpl implements MongoDBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBServiceImpl.class);

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
        mongoTemplate.insert(peoples,"people");
        return true;
    }

    public List<People> findListByAge(int age){
        Query query = new Query(Criteria.where("age").is(age));
        return mongoTemplate.find(query,People.class);
    }

    public List<People> findByNameAndAge(String name,int age){
        Query query = new Query(Criteria.where("age").is(age));
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query,People.class);
    }

    public void updateAddress(String name,int age,String updateAddress){
        Query query = new Query(Criteria.where("age").is(age));
        query.addCriteria(Criteria.where("name").is(name));
        Update update = Update.update("address",updateAddress);
        mongoTemplate.updateFirst(query,update,People.class);
    }

    @Override
    public String test() {
        LOGGER.info("First ---");
        return "First MongoDBServiceImpl";
    }

}
