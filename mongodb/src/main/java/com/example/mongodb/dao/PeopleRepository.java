package com.example.mongodb.dao;

import com.example.mongodb.entity.People;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@Repository
public interface PeopleRepository extends MongoRepository<People,String> {
}
