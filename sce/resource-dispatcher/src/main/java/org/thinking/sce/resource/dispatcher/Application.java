package org.thinking.sce.resource.dispatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thinkingframework.boot.web.mvc.AppWebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EntityScan(basePackages = "org.thinking.sce.service.core.domain")
@EnableJpaRepositories(basePackages = "org.thinking.sce.service.core.repository")
@ComponentScan(basePackages = {"org.thinking.sce.service.core.service", "org.thinking.sce.resource.dispatcher"})
@Slf4j
public class Application extends AppWebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}