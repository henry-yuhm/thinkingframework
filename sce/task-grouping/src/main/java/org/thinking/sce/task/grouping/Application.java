package org.thinking.sce.task.grouping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thinkingframework.boot.web.mvc.AppWebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EntityScan(basePackages = "org.thinking.sce.service.core.domain")
@EnableJpaRepositories(basePackages = "org.thinking.sce.service.core.repository")
@ComponentScan(basePackages = "org.thinking.sce.service.core.service")
public class Application extends AppWebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}