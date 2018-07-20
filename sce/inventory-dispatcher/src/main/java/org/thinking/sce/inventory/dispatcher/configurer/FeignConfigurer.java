package org.thinking.sce.inventory.dispatcher.configurer;

import feign.Logger;
import org.springframework.context.annotation.*;

@Configuration
public class FeignConfigurer {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}