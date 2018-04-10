package org.thinking.logistics.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EntityScan(basePackages = {"org.thinking.logistics.core.entity", "org.thinking.logistics.inventory.entity"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}