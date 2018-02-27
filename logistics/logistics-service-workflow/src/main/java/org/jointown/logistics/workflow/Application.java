package org.jointown.logistics.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.thinkingframework.boot.web.AppWebMvcConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EntityScan(basePackages = "org.jointown.logistics.workflow.entity")
public class Application extends AppWebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}