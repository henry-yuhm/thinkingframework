package org.thinking.logistics.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.thinkingframework.boot.mvc.AppWebMvcConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class Application extends AppWebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}