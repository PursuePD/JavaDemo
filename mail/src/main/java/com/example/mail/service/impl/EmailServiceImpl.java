package com.example.mail.service.impl;

import com.example.mail.config.EmailConfig;
import com.example.mail.service.EmailService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author:cuijialei
 * @Date: 2018/8/8
 * @Describe
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String sendTo, String titel, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(titel);
        message.setText(content);
        mailSender.send(message);
    }
    /**
     * 发送简单邮件
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendAttachmentsMail(String sendTo, String titel, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(titel);
            helper.setText("这里是内容(带附件）"+content);

            ClassPathResource zip = new ClassPathResource("/aa.zip");
            ClassPathResource image = new ClassPathResource("/weixin.jpg");
            helper.addAttachment("aa.zip", zip);
            helper.addAttachment("weixin.jpg", image);

//            FileSystemResource file = new FileSystemResource(new File("aa.zip"));
//            helper.addAttachment("aa.zip",file);

            //mimeMessage.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            mailSender.send(mimeMessage);

        } catch (Exception e) {

        }
    }
    /**
     * 发送带附件的文件
     * @param sendTo
     * @param titel
     * @param content
     * @param file
     */
    @Override
    public void sendFile(String sendTo, String titel, String content, MultipartFile file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(titel);
            helper.setText("这里是内容(带附件）"+content);

            File dir = new File("D:/Test");
            if (!dir.exists()) {
                dir.mkdirs();  //判断并创建文件夹
            }
            byte[] bytes = file.getBytes();   //文件字节流
            File fileToSave = new File(dir.getAbsolutePath()  + File.separator +  file.getOriginalFilename());
            FileCopyUtils.copy(bytes, fileToSave);   //保存文件
            //添加附件
//            ClassPathResource sendfile= new ClassPathResource(fileToSave.getAbsolutePath());
//            InputStream is = new FileInputStream(fileToSave);
            helper.addAttachment(fileToSave.getName(), fileToSave);
//            FileSystemResource file = new FileSystemResource(new File("aa.zip"));
//            helper.addAttachment("aa.zip",file);

            //mimeMessage.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * 发送模板邮件
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content<key, 内容> 邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
    @Override
    public void sendTemplateMail(String sendTo, String titel, Map<String, Object> content, List<Pair<String, File>> attachments) {

    }
}
