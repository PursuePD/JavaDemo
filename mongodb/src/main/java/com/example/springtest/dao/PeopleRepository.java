package com.example.springtest.dao;

import com.example.springtest.entity.People;
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
