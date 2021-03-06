package com.example.springtest.controller;

import com.example.springtest.entity.People;
import com.example.springtest.entity.PeopleListModel;
import com.example.springtest.service.MongoDBService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@RestController
public class MongoDBController {
    @Autowired
    @Qualifier("mongoDBServiceImpl")
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

    @GetMapping("/users/age")
    @ApiOperation(value = "users",notes = "无")
    @ApiImplicitParam(paramType = "query",dataType = "int",name = "age",value = "年龄",required = true )
    public List<People> findByAge(@RequestParam("age") int age){
        return mongoDBService.findListByAge(age);
    }

    @PutMapping("/users/update/address")
    @ApiOperation(value = "修改地址",notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "name",value = "姓名",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "int",name = "age",value = "年龄",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "address",value = "地址",required = true )
    })
    public void findByNameAndAge(@RequestParam("name") String name,@RequestParam("age") int age,@RequestParam("address") String address){
        mongoDBService.updateAddress(name,age,address);
    }

}
