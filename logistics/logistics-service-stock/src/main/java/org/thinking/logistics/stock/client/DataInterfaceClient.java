package org.thinking.logistics.stock.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thinking.logistics.stock.configurer.FeignConfigurer;

import javax.validation.constraints.NotNull;

/**
 * Created by Henry on 2017/4/1.
 */
@FeignClient(name = "logistics-service-core",
        qualifier = "dataInterfaceClient",
        configuration = FeignConfigurer.class,
        fallbackFactory = DataInterfaceClientFallbackFactory.class,
        path = "/dataInterface")
public interface DataInterfaceClient {
    @RequestMapping(value = "/call",
            method = RequestMethod.POST)
    @NotNull
    String call(@RequestParam("serviceName") @NotNull String serviceName,
                @RequestParam("requestType") @NotNull String requestType,
                @RequestParam("serviceParameters") @NotNull String serviceParameters);
}