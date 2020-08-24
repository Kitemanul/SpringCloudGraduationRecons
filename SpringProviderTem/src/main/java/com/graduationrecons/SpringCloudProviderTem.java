package com.graduationrecons;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient  //自动注册到eureka
@EnableFeignClients
public class SpringCloudProviderTem {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderTem.class,args);
    }
}
