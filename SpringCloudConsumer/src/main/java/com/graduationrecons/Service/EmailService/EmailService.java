package com.graduationrecons.Service.EmailService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER")
public interface EmailService {

     @RequestMapping(value = "/SendEmail")
     public void SendEmail(@RequestParam("toEmail") String toEmail, @RequestParam("code") String code);
}
