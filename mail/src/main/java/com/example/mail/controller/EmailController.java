package com.example.mail.controller;

import com.example.mail.service.EmailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @PostMapping("/sendfile")
    @ApiOperation(value = "发邮件 带附件",notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "sendTo",value = "邮箱",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "titel",value = "标题",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "content",value = "内容",required = true )
    })
    public void sendFile(@RequestParam("sendTo") String sendTo,
                         @RequestParam("titel") String titel,
                         @RequestParam("content") String content,
                         @RequestParam(value = "filename", required = true) MultipartFile file){
        emailService.sendFile(sendTo,titel,content,file);
    }

    @GetMapping("/sendsteamcode")
    @ApiOperation(value = "发steam验证码",notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "sendTo",value = "邮箱",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "username",value = "账号",required = true ),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "code",value = "验证码",required = true )
    })
    public void sendSteamCode(@RequestParam("sendTo") String sendTo,
                               @RequestParam("username") String username,
                               @RequestParam("code") String code){
        emailService.sendSteamCode(sendTo,username,code);
    }



}
