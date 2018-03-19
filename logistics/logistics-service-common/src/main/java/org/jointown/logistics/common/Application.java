package org.jointown.logistics.common;

import org.jointown.logistics.common.domain.DataInterfaceParameter;
import org.jointown.logistics.common.domain.DataInterfaceResult;
import org.jointown.logistics.common.domain.StreamComputingResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
//@EnableConfigurationProperties(DataInterfaceConfigurer.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DataInterfaceParameter dataServiceParameter() {
        return new DataInterfaceParameter();
    }

    @Bean
    public DataInterfaceResult dataServiceResult() {
        return new DataInterfaceResult();
    }

    @Bean
    public StreamComputingResult streamComputingResult() {
        return new StreamComputingResult();
    }
}