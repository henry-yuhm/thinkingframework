package org.jointown.logistics.common.configurer;

import org.jointown.logistics.common.entity.DataInterfaceParameter;
import org.jointown.logistics.common.entity.DataInterfaceResult;
import org.jointown.logistics.common.entity.StreamComputingResult;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfigurer {
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