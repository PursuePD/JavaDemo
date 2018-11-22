package com.example.springtest.controller;

import com.example.springtest.config.SpringUtil;
import com.example.springtest.service.MongoDBService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@RestController
public class SpringTestController {

    @GetMapping("/spring/test")
    @ApiOperation(value = "user",notes = "无")
    @ApiImplicitParam(paramType = "query",dataType = "String",name = "name",value = "姓名",required = true )
    public String findBy(String name){
        MongoDBService mongoDBService = (MongoDBService)SpringUtil.getBean(name);
        return mongoDBService.test();
    }

}
