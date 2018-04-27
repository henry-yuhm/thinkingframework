package org.thinking.logistics.order.initialization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thinkingframework.boot.web.mvc.AppWebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EntityScan(basePackages = "org.thinking.logistics.services.core.entity")
@EnableJpaRepositories(basePackages = "org.thinking.logistics.services.core.repository")
@Slf4j
public class Application extends AppWebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}