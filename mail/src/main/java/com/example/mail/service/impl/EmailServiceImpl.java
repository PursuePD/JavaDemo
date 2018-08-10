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

import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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

            helper.setText(content,true);

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

    /**
     * 发送steam验证码 内嵌图片
     */
    public void sendSteamCode(String sendTo,String username,String code){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject("Your Steam account: Access from new web or mobile device");


            String userhead = username.substring(0,1);
            String userfollow = username.substring(1);

            //内嵌图片   "<img src=\"cid:logo\">"
            ClassPathResource logo= new ClassPathResource("/logo.png");
            ClassPathResource valve= new ClassPathResource("/VALVe.gif");
            helper.addInline("logo", logo);
            helper.addInline("valve", valve);

            StringBuffer str = new StringBuffer();
            str.append("<table style='width: 538px; background-color: #393836;' align='center' cellspacing='0' cellpadding='0'>                                                                                                                                                                                                                                                                                                                                                                                                       ");
            str.append("	<tbody><tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
            str.append("		<td style=' height: 65px; background-color: #000000; border-bottom: 1px solid #4d4b48;'>                                                                                                                                                                                                                                                                                                                                                                                                              ");
            str.append("              <img src=\"https://store.steampowered.com/public/shared/images/email/email_header_logo.png\" width='538' height='65'>                                                                                                                                                                                                                                                                                                                                                                             ");
//            str.append("              <img src='cid:logo' width='538' height='65'>                                                                                                                                                                                                                                                                                                                                                                             ");
            str.append("        </td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            str.append("	</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append("	<tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
            str.append("		<td bgcolor='#17212e'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ");
            str.append("			<table width='470' border='0' align='center' cellpadding='0' cellspacing='0' style='padding-left: 5px; padding-right: 5px; padding-bottom: 10px;'>                                                                                                                                                                                                                                                                                                                                                ");
            str.append("				<tbody><tr bgcolor='#17212e'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            str.append("					<td style='padding-top: 32px;'>                                                                                                                                                                                                                                                                                                                                                                                                                                                           ");
            str.append("					<span style='padding-top: 16px; padding-bottom: 16px; font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;'>                                                                                                                                                                                                                                                                                                                                    ");
            str.append("						Dear "+userhead+"<span style='border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;' t='7' onclick='return false;' data='121312123' isout='1'>"+userfollow+"</span>,                                                                                                                                                                                                                                                                                                            ");
            str.append("					</span><br>                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
            str.append("					</td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append("				</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ");
            str.append("				<tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
            str.append("					<td style='padding-top: 12px;'>                                                                                                                                                                                                                                                                                                                                                                                                                                                           ");
            str.append("					<span style='font-size: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif; font-weight: bold;'>                                                                                                                                                                                                                                                                                                                                                                             ");
            str.append("						<p>Here is the Steam Guard code you need to login to account "+userhead+"<span style='border-bottom:1px dashed #ccc;z-index:1' t='7' onclick='return false;' data='121312123'>"+userfollow+"</span>:</p>                                                                                                                                                                                                                                                                                              ");
            str.append("					</span>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
            str.append("					</td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append("				</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ");
            str.append("				<tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
            str.append("					<td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
            str.append("						<div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            str.append("							<span style='font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;'>"+code+"</span>                                                                                                                                                                                                                                                                                                                                                         ");
            str.append("						</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ");
            str.append("					</td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append("				</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ");
            str.append("				<tr bgcolor='#121a25'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ");
            str.append("					<td style='padding: 20px; font-size: 12px; line-height: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif;'>                                                                                                                                                                                                                                                                                                                                                                ");
            str.append("							<p style='padding-bottom: 10px; color: #c6d4df;'>This email was generated because of a login attempt from a web or mobile device <a style='color: #c6d4df;' href='https://steamcommunity.com/actions/ReportSuspiciousLogin?stoken=3ed8f2e47f01dcc0f5c8a9ac26782c998d7295a1ec485e7c18c839ef3765be381117cbcb5a087d793b9fb22b25dba77d' rel='noopener' target='_blank'>located at 85.113.34.77 (RU).</a>  The login attempt included your correct account name and password.</p>      ");
            str.append("							<p style='padding-bottom: 10px; color: #c6d4df;'>The Steam Guard code is required to complete the login.  <span style='color: #ffffff; font-weight: bold;'>No one can access your account without also accessing this email.</span></p>                                                                                                                                                                                                                                           ");
            str.append("							<p style='padding-bottom: 10px; color: #c6d4df;'><span style='color: #ffffff; font-weight: bold;'>If you are not attempting to login</span> then please change your Steam password, and consider changing your email password as well to ensure your account security.</p>                                                                                                                                                                                                        ");
            str.append("							<p style='padding-top: 10px; color: #61696d;'>If you are unable to access your account then <a style='color: #8f98a0;' href='https://help.steampowered.com/en/wizard/HelpUnauthorizedLogin?stoken=AeX3V5EclawPbqh4WyspaV9u7Bs0CUjlfAsOsZRqg2tuK7OwUVb4KlHczsZMJafB3lSiGFTdEpF8JR1qBwIxyg%3D%3D' rel='noopener' target='_blank'>use this account specific recovery link</a> for assistance recovering or self-locking your account.</p>                                            ");
            str.append("					</td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append("				</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ");
            str.append("				<tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
            str.append("					<td style='font-size: 12px; color: #6d7880; padding-top: 16px; padding-bottom: 60px;'>                                                                                                                                                                                                                                                                                                                                                                                                    ");
            str.append("                    			The Steam Team<br>                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
            str.append("                    			<a style='color: #8f98a0;' href='https://help.steampowered.com' rel='noopener' target='_blank'>https://help.steampowered.com</a><br>                                                                                                                                                                                                                                                                                                                                          ");
            str.append("                    </td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append("				</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ");
            str.append("			</tbody></table>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ");
            str.append("		</td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            str.append("	</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append(" <tr><td bgcolor='#000000'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
            str.append("     	<table width='460' height='55' border='0' align='center' cellpadding='0' cellspacing='0'>                                                                                                                                                                                                                                                                                                                                                                                                             ");
            str.append("       	 <tbody><tr valign='top'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                             ");
            str.append("          <td width='110'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ");
            str.append("            <a href='http://www.valvesoftware.com/' target='_blank' style=' color: #8B8B8B; font-size: 10px; font-family: Trebuchet MS, Verdana, Arial, Helvetica, sans-serif; text-transform: uppercase;  ' font-size:='font-size:' px='px' color:='color:' b8b8b='b8b8b' font-family:='font-family:' trebuchet='trebuchet' ms='ms' text-transform:='text-transform:' uppercase='uppercase' rel='noopener'>                                                                                                  ");
//            str.append("			<img src=\"http://storefront.steampowered.com/v/img/gift/VALVe.gif\" width='92' height='26' hspace='0' vspace='0' border='0' align='top'><span></span></a></td>                                                                                                                                                                                                                                                                                                                                     ");
            str.append("			<img src='cid:valve' width='92' height='26' hspace='0' vspace='0' border='0' align='top'><span></span></a></td>                                                                                                                                                                                                                                                                                                                                     ");
            str.append("		  <td width='350' valign='top'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ");
            str.append("		   <span style='color: #999999; font-size: 9px; font-family: Verdana, Arial, Helvetica, sans-serif;'>© Valve Corporation. All rights reserved. All trademarks are property of their respective owners in the US and other countries.</span>                                                                                                                                                                                                                                                           ");
            str.append("		  </td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
            str.append("       	 </tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ");
            str.append("        </tbody></table>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ");
            str.append("	</td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
            str.append("  </tr><tr></tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ");
            str.append("</tbody></table>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ");

            helper.setText(str.toString(),true);


            mailSender.send(mimeMessage);

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

}
