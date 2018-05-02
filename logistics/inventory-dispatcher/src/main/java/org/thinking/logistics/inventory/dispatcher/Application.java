package org.thinking.logistics.inventory.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.thinkingframework.boot.web.mvc.AppWebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EntityScan(basePackages = {"org.thinking.logistics.core.domain", "org.thinking.logistics.inventory.domain"})
public class Application extends AppWebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}