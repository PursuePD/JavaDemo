package com.example.mail.controller;

import com.example.mail.service.EventService;
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
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/eventMessage")
    @ApiOperation(value = "触发事件",notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "message",value = "消息",required = true )
    })
    public boolean sendSimpleMail(@RequestParam("message") String message){
        return eventService.triggerMessage(message);
    }
}
