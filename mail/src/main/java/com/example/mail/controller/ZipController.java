package com.example.mail.controller;

import com.example.mail.service.ZipService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:cuijialei
 * @Date: 2018/8/8
 * @Describe
 */
@RestController
public class ZipController {

    @Autowired
    ZipService zipService;

    @GetMapping("/zip")
    @ApiOperation(value = "压缩加密",notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "password",value = "密码",required = true )
    })
    public void sendSimpleMail(@RequestParam("password") String password){
        zipService.zipFile(password);
    }
}
