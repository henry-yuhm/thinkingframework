package org.jointown.logistics.core.configurer;

import org.jointown.logistics.core.repository.ParameterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "org.jointown.logistics.core.repository")
public class BeanConfigurer {
    @Bean
    public Map<String, String> parameters(ParameterRepository repository) {
        Map<String, String> parameters = new HashMap<>();

        repository.findAll().forEach(parameter -> parameters.put(parameter.getNumber(), parameter.getValue()));

        return parameters;
    }
}