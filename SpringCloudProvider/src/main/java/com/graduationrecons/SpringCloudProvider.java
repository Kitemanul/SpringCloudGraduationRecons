package com.graduationrecons;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  //自动注册到eureka
public class SpringCloudProvider {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProvider.class,args);
    }
}
