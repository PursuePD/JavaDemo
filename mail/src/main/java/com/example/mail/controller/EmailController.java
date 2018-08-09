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


    /**
     * 上传文件。
     * @param file, 文件字节流， 其中filename是multipart中的变量名称
     * @return
     */
    @PostMapping("/upload")
    public void uploadFile(/**HttpServletRequest request, 拿到请求的所有信息 */
                                  //@RequestHeader(value = "Range") String range,   //大文件分段上传，格式： Range: start-end/total
//                                  @RequestHeader(value = "Content-Type") String type,
                                  //@RequestParam(value = "signparam") String data,  //在http包体里添加验签的json
                                  @RequestParam(value = "filename", required = true)MultipartFile file) {
        //System.out.println(request.getHeader("Content-Type"));   //文件分片或断点续传需要Range头，表示字节流对应原始文件的位置
//        System.out.println("请求类型：" + type);
//        PictureResp resp = new PictureResp();
        try {
            File dir = new File("D:/Test");
            if (!dir.exists()) {
                dir.mkdirs();  //判断并创建文件夹
            }

            byte[] bytes = file.getBytes();   //文件字节流
            File fileToSave = new File(dir.getAbsolutePath()  + File.separator +  file.getOriginalFilename());

            FileCopyUtils.copy(bytes, fileToSave);   //保存文件

            //数据表里要存储原始文件名、用户id、时间戳、服务器生成的文件名， 客户端文件起始位置start、客户端文件终止位置end、客户端文件大小total等
//            resp.setStatus(0);
//            resp.setSize(fileToSave.length());
//            resp.setPath(fileToSave.getPath());
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

}
