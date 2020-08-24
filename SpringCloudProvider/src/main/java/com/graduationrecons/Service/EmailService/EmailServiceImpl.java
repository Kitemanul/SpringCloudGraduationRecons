package com.graduationrecons.Service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@Api("邮件发送Service")
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendFrom;
    @Override
    @ApiOperation("发送邮件")
    public void SendEmail(String toEmail, String code) {


        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setSubject("窖池管理系统验证码");
        simpleMailMessage.setText("验证码为："+code);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setFrom(sendFrom);
        javaMailSender.send(simpleMailMessage);

    }
}
