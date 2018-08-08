package com.example.mail.controller;

import com.example.mail.service.EmailService;
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
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/simplemail")
    @ApiOperation(value = "发邮件",notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "sendTo",value = "邮箱",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "titel",value = "标题",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "content",value = "内容",required = true )
    })
    public void sendSimpleMail(@RequestParam("sendTo") String sendTo,
                               @RequestParam("titel") String titel,
                               @RequestParam("content") String content){
        emailService.sendSimpleMail(sendTo,titel,content);
    }

    @GetMapping("/attachmentsmail")
    @ApiOperation(value = "发邮件 带附件",notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "sendTo",value = "邮箱",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "titel",value = "标题",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "content",value = "内容",required = true )
    })
    public void sendAttachmentsMail(@RequestParam("sendTo") String sendTo,
                               @RequestParam("titel") String titel,
                               @RequestParam("content") String content){
        emailService.sendAttachmentsMail(sendTo,titel,content);
    }
}
