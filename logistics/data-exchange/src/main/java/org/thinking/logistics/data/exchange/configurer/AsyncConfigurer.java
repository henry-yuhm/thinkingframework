package org.thinking.logistics.data.exchange.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfigurer {
    @Value("business.executor.corePoolSize")
    private String corePoolSize;

    @Value("business.executor.maxPoolSize")
    private String maxPoolSize;

    @Value("business.executor.queueCapacity")
    private String queueCapacity;

    @Bean
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }
}