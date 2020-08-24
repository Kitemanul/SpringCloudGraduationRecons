package com.graduationrecons;


import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient  //自动注册到eureka
@EnableFeignClients
public class SpringCloudProviderTem {
    public static void main(String[] args) {

        SpringApplication.run(SpringCloudProviderTem.class,args);
    }

    @Bean
    public ServletRegistrationBean hystrixBean()
    {
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/actuator/hystrix.stream");

        return registrationBean;
    }
}
