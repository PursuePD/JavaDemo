package com.example.mongodb.controller;

import com.example.mongodb.entity.People;
import com.example.mongodb.entity.PeopleListModel;
import com.example.mongodb.service.MongoDBService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@RestController
public class MongoDBController {
    @Autowired
    MongoDBService mongoDBService;

    @GetMapping("/user/name")
    @ApiOperation(value = "user",notes = "无")
    @ApiImplicitParam(paramType = "query",dataType = "String",name = "name",value = "姓名",required = true )
    public People findBy(String name){
        return mongoDBService.findBy(name);
    }

    @PostMapping("/user/add")
    @ApiOperation(value = "user",notes = "无")
    @ApiImplicitParam(paramType = "body",dataType = "People",name = "people",value = "人",required = true )
    public boolean addPeople(@RequestBody People people){
        return mongoDBService.addPeople(people);
    }

    @PostMapping("/user/addlist")
    @ApiOperation(value = "user",notes = "无")
    @ApiImplicitParam(paramType = "body",dataType = "PeopleListModel",name = "peopleListModel",value = "人员列表",required = true )
    public boolean addPeoples(@RequestBody PeopleListModel peopleListModel){
        return mongoDBService.addPeoples(peopleListModel.getPeoples());
    }

}
